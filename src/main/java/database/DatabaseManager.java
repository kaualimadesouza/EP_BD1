package database;

import java.sql.*;
import java.util.HashMap;
import java.util.StringJoiner;

public class DatabaseManager {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    public DatabaseManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet retrive(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(query);
    }

    public int insert(String tableName,HashMap<String, Object> insertRow) throws SQLException {
        StringJoiner columns = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ");

        for (String key : insertRow.keySet()) {
            columns.add(key);
            values.add("?");
        }

        String QUERY = String.format("insert into %s (%s) values (%s)", tableName, columns, values);

        try (PreparedStatement statement = this.connection.prepareStatement(QUERY)) {
            int index = 1;
            for (Object value : insertRow.values()) {
                statement.setObject(index++, value);
            }

            return statement.executeUpdate();
        }
    }

    public int update(String tableName, HashMap<String, Object> updateRow, String where) throws SQLException {
        StringJoiner columns_value = new StringJoiner(", ");

        for (String key : updateRow.keySet()) {
            columns_value.add(key + " = ?");
        }

        System.out.println(columns_value);

        String QUERY = String.format("update %s set %s where %s", tableName, columns_value, where);

        try (PreparedStatement statement = this.connection.prepareStatement(QUERY)) {
            int index = 1;
            for (Object value: updateRow.values()) {
                statement.setObject(index++, value);
            }
            return statement.executeUpdate();
        }
    }

    public int delete(String tableName, String whereCondition) throws SQLException {
        String QUERY = String.format("DELETE FROM %s WHERE %s", tableName, whereCondition);
        Statement statement = this.connection.createStatement();
        return statement.executeUpdate(QUERY);
    }
}
