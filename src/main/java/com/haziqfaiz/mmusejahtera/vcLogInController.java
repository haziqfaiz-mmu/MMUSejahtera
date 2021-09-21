package com.haziqfaiz.mmusejahtera;

import com.haziqfaiz.mmusejahtera.recipientLogInDAO;
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

public class vcLogInController {
    @FXML
    private TextField IDField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button logInButton;

    @FXML
    private TextFlow recipientAdminText;

    @FXML
    public void initialize() {

        Hyperlink hyperLink2 = new Hyperlink("admin");
        hyperLink2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    mohHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        Hyperlink hyperLink3 = new Hyperlink("recipient");
        hyperLink3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                try{
                    recipientHyperLink(event);
                }
                catch (IOException e){}

            }
        });
        recipientAdminText.getChildren().add(new Text("Or login as an"));
        recipientAdminText.getChildren().add(hyperLink2);
        recipientAdminText.getChildren().add(new Text("or a"));
        recipientAdminText.getChildren().add(hyperLink3);



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


    public void mohHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mohLogIn.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("MOH Login");
        stage.setScene(scene);
        stage.show();
    }

    public void recipientHyperLink(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("recipientLogIn.fxml"));

        Scene scene = new Scene(root, 1000, 700);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
