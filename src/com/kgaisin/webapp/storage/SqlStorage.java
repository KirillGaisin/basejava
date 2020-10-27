package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.ContactType;
import com.kgaisin.webapp.model.Link;
import com.kgaisin.webapp.model.Resume;
import com.kgaisin.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execStatement("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return getContacts(uuid);
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execStatement("UPDATE resume SET full_name =? WHERE uuid =?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new ResumeNotFoundException(r.getUuid());
            }
            return null;
        });
        deleteContacts(r);
        insertContacts(r);
    }

    @Override
    public void save(Resume r) {
        sqlHelper.execStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
        insertContacts(r);
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execStatement("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new ResumeNotFoundException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execStatement("SELECT * FROM resume ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(getContacts(rs.getString("uuid")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execStatement("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertContacts(Resume r) {
        sqlHelper.execStatement(
                "INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)", ps -> {
                    for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, e.getKey().name());
                        ps.setString(3, e.getValue());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                    return null;
                });
    }

    private Resume getContacts(String uuid) {
        return sqlHelper.execStatement("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new ResumeNotFoundException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        r.addContact(type, value);
                    } while (rs.next());

                    return r;
                });
    }

    private void deleteContacts(Resume r) {
        sqlHelper.execStatement("DELETE FROM contact WHERE resume_uuid =?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }
}
