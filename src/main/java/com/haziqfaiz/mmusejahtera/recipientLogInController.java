package com.haziqfaiz.mmusejahtera;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;


import java.io.IOException;
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
    private TextFlow vcAdminText;


    @FXML
    public void initialize() {

        registerText.getChildren().add(new Text("Not registered?"));
        Hyperlink hyperLink = new Hyperlink("Register Now!");
        hyperLink.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                registerHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        registerText.getChildren().add(hyperLink);

        Hyperlink hyperLink2 = new Hyperlink("admin");
        hyperLink2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    mohHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        Hyperlink hyperLink3 = new Hyperlink("vaccination center");
        hyperLink3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    vcHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        vcAdminText.getChildren().add(new Text("Or login as an"));
        vcAdminText.getChildren().add(hyperLink2);
        vcAdminText.getChildren().add(new Text("or a"));
        vcAdminText.getChildren().add(hyperLink3);



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

        recipientDAO jdbcDao = new recipientDAO();
        boolean flag = jdbcDao.validate(Id, password);

        if (!flag) {
            infoBox("Please enter correct IC/Passport number and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Failed");
        }
    }

    public void registerHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("recipientRegister.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("User Registration");
        stage.setScene(scene);
        stage.show();
    }

    public void mohHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mohLogIn.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("MOH Login");
        stage.setScene(scene);
        stage.show();
    }

    public void vcHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("vcLogIn.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Vaccine Center Login");
        stage.setScene(scene);
        stage.show();
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