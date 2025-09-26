package org.goalytics;

import database.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int inserirExemplo(DatabaseManager databaseManager) throws SQLException {
        HashMap<String, Object> row = new HashMap<>();
        row.put("nome", "Copa Brasil");
        row.put("temporada", 1993);
        row.put("tipo_campeonato", "Nacional");
        row.put("status", "Em andamento");
        row.put("campeao", null); // ainda não tem campeão

        // Insere uma linha de dados na tabela
        return databaseManager.insert("Campeonato", row);
    }

    public static void obterDadoExemplo(DatabaseManager databaseManager) throws SQLException {
        ResultSet result = databaseManager.retrive("SELECT * FROM Campeonato");
        while (result.next()) {
            System.out.println(result.getString("nome"));
            System.out.println(result.getInt("temporada"));
            System.out.println(result.getString("tipo_campeonato"));
            System.out.println(result.getString("status"));
            System.out.println(result.getString("campeao"));
        }
    }

    public static int removerExemplo(DatabaseManager databaseManager) throws SQLException {
        return databaseManager.delete("Campeonato", "id = 1");
    }

    public static int atualizarExemplo(DatabaseManager databaseManager) throws SQLException {
        HashMap<String, Object> row2 = new HashMap<>();
        row2.put("nome", "Copa");
        row2.put("status", "Completo");
        return databaseManager.update("Campeonato", row2, "id = 1;");
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String usuario = "postgres.xevlercinmqwjvjyxpmu";
        String senha = "RWCy1fwr7&!YZYuLu$g4";

        DatabaseManager databaseManager = new DatabaseManager(url, usuario, senha);

        // Insere linha no Banco de dados
        int resultInserir = inserirExemplo(databaseManager);
        System.out.println("Linhas inseridas: " + resultInserir);
        System.out.println("=============================");

        // Obtem dados do Banco dados
        obterDadoExemplo(databaseManager);
        System.out.println("=============================");

        // Remove um item pelo id
        int resultRemover = removerExemplo(databaseManager);
        System.out.println("Linhas removidas: " + resultRemover);
        System.out.println("=============================");

        // Atualiza um item pelo id
        int resultAtualizar = atualizarExemplo(databaseManager);
        System.out.println("Linhas atualizadas: " + resultAtualizar);
    }
}