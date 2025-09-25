package org.goalytics;

import database.DatabaseManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public void inserirExemplo(DatabaseManager databaseManager) {

    }

    public void obterDadoExemplo(DatabaseManager databaseManager) {

    }

    public void removerExemplo(DatabaseManager databaseManager) {

    }

    public void atualizarExemplo(DatabaseManager databaseManager) {

    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres";
        String usuario = "postgres.xevlercinmqwjvjyxpmu";
        String senha = "RWCy1fwr7&!YZYuLu$g4";

        DatabaseManager databaseManager = new DatabaseManager(url, usuario, senha);


    }
}