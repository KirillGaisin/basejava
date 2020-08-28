package com.kgaisin.webapp.storage;

import com.kgaisin.webapp.exception.ResumeNotFoundException;
import com.kgaisin.webapp.model.Resume;
import com.kgaisin.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
        return sqlHelper.execStatement("SELECT * FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ResumeNotFoundException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execStatement("UPDATE resume SET full_name =? WHERE uuid =?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        // не понимаю про return!!! почему return null
        sqlHelper.execStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execStatement("DELETE FROM resume WHERE uuid =?", ps -> {
            ps.setString(1, uuid);
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execStatement("SELECT * FROM resume ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while(rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execStatement("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        });
    }
}
