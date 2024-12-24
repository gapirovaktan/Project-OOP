package com.example.projectjava;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController {
    @FXML
    private Label welcome;

    @FXML
    private DatePicker date;

    @FXML
    protected void onButtonclick() {
        welcome.setText("This project was made by Aktan and Izat");
    }

    @FXML
    protected void onScheduleButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) welcome.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }

        LocalDate selectedDate = date.getValue();
        if (selectedDate != null) {
            welcome.setText("Scheduled event on: " + selectedDate);
        } else {
            welcome.setText("Please select a date!");
        }
    }

    @FXML
    private TextField TextField1;

    @FXML
    private TextField TextField2;

    @FXML
    private TextField TextField3;

    @FXML
    private TextField TextField4;

    @FXML
    private TextField TextField5;

    @FXML
    private TextField TextField6;

    @FXML
    private TextField TextField7;

    @FXML
    private TextField TextField8;

    @FXML
    private TextField TextField9;

    @FXML
    private TextField TextField10;

    @FXML
    private TextField TextField11;

    @FXML
    private TextField TextField12;

    @FXML
    private TextField TextField13;

    @FXML
    private TextField TextField14;

    @FXML
    private TextField TextField15;

    @FXML
    private TextField TextField16;

    @FXML
    protected void onContinueClick () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("3.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) date.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSaveClick() {
        LocalDate selectedDate = date.getValue();
        StringBuilder details = new StringBuilder();

        if (date == null) {
            welcome.setText("DatePicker is not initialized. Check your FXML file.");
            return;
        }

        if (selectedDate == null) {
            welcome.setText("Please select a date before saving!");
            return;
        }


        TextField[] fields = {TextField1, TextField2, TextField3, TextField4, TextField5, TextField6, TextField7, TextField8,
                TextField9, TextField10, TextField11, TextField12, TextField13, TextField14, TextField15, TextField16};

        for (TextField field : fields) {
            if (field.getText() != null && !field.getText().isEmpty()) {
                details.append(field.getText()).append(" ");
            }
        }

        if (selectedDate != null && details.length() > 0) {
            try (Connection conn = Appointments.getConnection()) {
                String sql = "INSERT INTO appointments (appointment_date, details) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setDate(1, java.sql.Date.valueOf(selectedDate));
                stmt.setString(2, details.toString().trim());
                stmt.executeUpdate();

                welcome.setText("Appointment saved successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                welcome.setText("Error saving appointment.");
            }
        } else {
            welcome.setText("Please select a date and enter details!");
        }
    }

    @FXML
    protected void onDeleteClick() {
        LocalDate selectedDate = date.getValue();

        if (selectedDate != null) {
            try (Connection conn = Appointments.getConnection()) {
                String sql = "DELETE FROM appointments WHERE appointment_date = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setDate(1, java.sql.Date.valueOf(selectedDate));
                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    welcome.setText("Appointment deleted successfully!");
                } else {
                    welcome.setText("No appointment found for the selected date.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                welcome.setText("Error deleting appointment.");
            }
        } else {
            welcome.setText("Please select a date to delete!");
        }
    }
}
