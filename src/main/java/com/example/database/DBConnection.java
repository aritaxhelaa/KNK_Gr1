package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    private static final String DB_URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Lidhja me PostgreSQL u realizua me sukses!");
            } catch (SQLException e) {
                System.out.println("Gabim gjatë lidhjes me PostgreSQL!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}


