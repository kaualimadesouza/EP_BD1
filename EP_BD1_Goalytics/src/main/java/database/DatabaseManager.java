package database;

import java.sql.*;

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
            Connection connection = DriverManager.getConnection(url, user, password);
            this.connection = connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void retrive() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT COUNT(*)");
        while(result.next()) {
            int count = result.getInt("count");
            System.out.println(count);
        }
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String usuario = "postgres.xevlercinmqwjvjyxpmu";
        String senha = "RWCy1fwr7&!YZYuLu$g4";

        DatabaseManager databaseManager = new DatabaseManager(url, usuario, senha);
        databaseManager.retrive();
    }
}
