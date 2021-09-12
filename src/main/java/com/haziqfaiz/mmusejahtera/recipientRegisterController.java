package com.haziqfaiz.mmusejahtera;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;


public class recipientRegisterController {

    @FXML
    private TextField idField, DOBField, fullNameField, phoneNumberField, emailField, addressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private TextFlow logInText;

    @FXML
    public void initialize() {
        //registerText = new TextFlow(new Text("Not registered?"));
        logInText.getChildren().add(new Text("Back to"));
        Hyperlink hyperLink = new Hyperlink("log in");
        hyperLink.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    logInHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        logInText.getChildren().add(hyperLink);
    }



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

    public void logInHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("recipientLogIn.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("User Log In");
        stage.setScene(scene);
        stage.show();
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



