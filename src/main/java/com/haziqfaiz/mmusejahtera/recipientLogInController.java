package com.haziqfaiz.mmusejahtera;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;

public class recipientLogInController {
    @FXML
    private TextField IDField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private TextFlow registerText;


    @FXML
    public void initialize() {
        //registerText = new TextFlow(new Text("Not registered?"));
        registerText.getChildren().add(new Text("Not registered?"));
        registerText.getChildren().add(new Hyperlink("Register Now"));
    }

    public void login(ActionEvent event) throws SQLException {
        Window owner = logInButton.getScene().getWindow();

        System.out.println(IDField.getText());
        System.out.println(passwordField.getText());

        if (IDField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String Id = IDField.getText();
        String password = passwordField.getText();

        recipientLogInDAO jdbcDao = new recipientLogInDAO();
        boolean flag = jdbcDao.validate(Id, password);

        if (!flag) {
            infoBox("Please enter correct IC/Passport number and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
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