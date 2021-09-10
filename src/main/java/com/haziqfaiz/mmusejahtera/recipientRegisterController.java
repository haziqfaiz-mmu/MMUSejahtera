package com.haziqfaiz.mmusejahtera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import java.sql.SQLException;


public class recipientRegisterController {

    @FXML
    private TextField idField, DOBField, fullNameField, phoneNumberField, emailField, addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;



    public void register(ActionEvent event) throws SQLException {
        Window owner = submitButton.getScene().getWindow();


        System.out.println(idField.getText());
        System.out.println(fullNameField.getText());
        System.out.println(DOBField.getText());
        System.out.println(phoneNumberField.getText());
        System.out.println(emailField.getText());
        System.out.println(addressField.getText());
        System.out.println(passwordField.getText());

        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (idField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your IC or Passport Number");
            return;
        }

        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String id = idField.getText();
        String fullName = fullNameField.getText();
        String password = passwordField.getText();

        recipientRegisterDAO dao = new recipientRegisterDAO();
        dao.insertRecord(id,password,fullName);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome to MMUSejahtera" + fullNameField.getText());
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}



