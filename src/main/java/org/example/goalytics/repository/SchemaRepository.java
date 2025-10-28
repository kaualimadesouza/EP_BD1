package org.example.goalytics.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SchemaRepository {
    private final DataSource dataSource;

    public SchemaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> listAllTables() {
        String sql = "SELECT table_name\n" +
                "FROM information_schema.tables\n" +
                "WHERE table_schema = 'public'\n" +
                "  AND table_type = 'BASE TABLE'\n" +
                "ORDER BY table_name;";

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            List<String> tables = new ArrayList<>();
            while (resultSet.next()) {
                tables.add(resultSet.getString(1));
            }
            return tables;

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter a lista de tabelas", e);
        }
    }
}
