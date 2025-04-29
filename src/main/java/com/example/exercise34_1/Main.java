package com.example.exercise34_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    private TextField tfID = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMI = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();

    @Override
    public void start(Stage stage) {
        // GridPane for form fields
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setPadding(new Insets(20));

        int row = 0;
        formGrid.add(new Label("ID:"), 0, row);
        formGrid.add(tfID, 1, row++);
        formGrid.add(new Label("Last Name:"), 0, row);
        formGrid.add(tfLastName, 1, row++);
        formGrid.add(new Label("First Name:"), 0, row);
        formGrid.add(tfFirstName, 1, row++);
        formGrid.add(new Label("MI:"), 0, row);
        formGrid.add(tfMI, 1, row++);
        formGrid.add(new Label("Address:"), 0, row);
        formGrid.add(tfAddress, 1, row++);
        formGrid.add(new Label("City:"), 0, row);
        formGrid.add(tfCity, 1, row++);
        formGrid.add(new Label("State:"), 0, row);
        formGrid.add(tfState, 1, row++);
        formGrid.add(new Label("Telephone:"), 0, row);
        formGrid.add(tfTelephone, 1, row++);
        formGrid.add(new Label("Email:"), 0, row);
        formGrid.add(tfEmail, 1, row++);

        // Buttons
        Button btnInsert = new Button("Insert");
        Button btnView = new Button("View");
        Button btnUpdate = new Button("Update");
        Button btnClear = new Button("Clear");

        btnInsert.setOnAction(e -> insertRecord());
        btnView.setOnAction(e -> viewRecord());
        btnUpdate.setOnAction(e -> updateRecord());
        btnClear.setOnAction(e -> clearFields());

        HBox buttons = new HBox(15, btnInsert, btnView, btnUpdate, btnClear);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, formGrid, buttons);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Disable ID field when inserting
        tfID.setDisable(true);

        Scene scene = new Scene(layout, 400, 550);
        stage.setTitle("Staff Information");
        stage.setScene(scene);
        stage.show();
    }

    private void insertRecord() {
        String sql = "INSERT INTO Staff (lastName, firstName, mi, address, city, state, telephone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tfLastName.getText());
            ps.setString(2, tfFirstName.getText());
            ps.setString(3, tfMI.getText());
            ps.setString(4, tfAddress.getText());
            ps.setString(5, tfCity.getText());
            ps.setString(6, tfState.getText());
            ps.setString(7, tfTelephone.getText());
            ps.setString(8, tfEmail.getText());

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                // Fetch the new ID
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    tfID.setText(String.valueOf(generatedKeys.getInt(1)));
                }
                showAlert(Alert.AlertType.INFORMATION, "Success", "Record inserted successfully!");
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert record: " + e.getMessage());
        }
    }

    private void viewRecord() {
        String id = tfID.getText();
        if (id.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter an ID to view.");
            return;
        }

        String sql = "SELECT * FROM Staff WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfMI.setText(rs.getString("mi"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfTelephone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Not Found", "No record found for ID: " + id);
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to view record: " + e.getMessage());
        }
    }

    private void updateRecord() {
        String id = tfID.getText();
        if (id.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please enter an ID to update.");
            return;
        }

        String sql = "UPDATE Staff SET lastName=?, firstName=?, mi=?, address=?, city=?, state=?, telephone=?, email=? WHERE id=?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tfLastName.getText());
            ps.setString(2, tfFirstName.getText());
            ps.setString(3, tfMI.getText());
            ps.setString(4, tfAddress.getText());
            ps.setString(5, tfCity.getText());
            ps.setString(6, tfState.getText());
            ps.setString(7, tfTelephone.getText());
            ps.setString(8, tfEmail.getText());
            ps.setString(9, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Record updated successfully!");
            } else {
                showAlert(Alert.AlertType.WARNING, "Warning", "No record found to update!");
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update record: " + e.getMessage());
        }
    }

    private void clearFields() {
        tfID.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfMI.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfTelephone.clear();
        tfEmail.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    public static void main(String[] args) {
        launch();
    }
}