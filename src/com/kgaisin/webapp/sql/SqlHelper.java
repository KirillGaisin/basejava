package com.kgaisin.webapp.sql;

import com.kgaisin.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execStatement(String sql) {
        execStatement(sql, PreparedStatement::execute);
    }

    public <T> T execStatement(String sql, SqlExecutor<T> executor) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return executor.execute(statement);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface SqlExecutor<T> {
        T execute(PreparedStatement statement) throws SQLException;
    }
}
