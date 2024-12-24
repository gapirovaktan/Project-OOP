package com.example.projectjava;

import java.sql.Connection;
import java.sql.SQLException;

public class TestAppointments {
    public static void main(String[] args) {
        try (Connection connection = Appointments.getConnection()) {
            if (connection != null) {
                System.out.println("Successfully connected to the database!");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

