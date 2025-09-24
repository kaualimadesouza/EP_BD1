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

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String usuario = "postgres.xevlercinmqwjvjyxpmu";
        String senha = "RWCy1fwr7&!YZYuLu$g4";

        DatabaseManager databaseManager = new DatabaseManager(url, usuario, senha);

        HashMap<String, Object> row = new HashMap<>();
        row.put("nome", "Copa Brasil");
        row.put("temporada", 1992);
        row.put("tipo_campeonato", "Nacional");
        row.put("status", "Em andamento");
        row.put("campeao", null); // ainda não tem campeão

        // Insere uma linha de dados na tabela
        databaseManager.insert("Campeonato", row);

        // Busca dados na tabela
        ResultSet result = databaseManager.retrive("SELECT * FROM Campeonato");
        while (result.next()) {
            System.out.println(result.getString("nome"));
            System.out.println(result.getInt("temporada"));
            System.out.println(result.getString("tipo_campeonato"));
            System.out.println(result.getString("status"));
            System.out.println(result.getString("campeao"));
        }

        // Update de dados
        HashMap<String, Object> row2 = new HashMap<>();
        row2.put("nome", "Copa");
        row2.put("status", "Completo");
        databaseManager.update("Campeonato", row2, "id = 1;");

        int linhasApagadas = databaseManager.delete("Campeonato", "id = 1");
        System.out.println(linhasApagadas);
    }
}
