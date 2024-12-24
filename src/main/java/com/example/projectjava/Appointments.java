package com.example.projectjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Appointments {
        private static String URL = "jdbc:postgresql://localhost:5432/project.java";
        private static String USER = "postgres";
        private static String PASSWORD = "220506";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);

        }
}
