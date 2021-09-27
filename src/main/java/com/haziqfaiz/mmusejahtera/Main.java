package com.haziqfaiz.mmusejahtera;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(getClass());

        Parent root = FXMLLoader.load(getClass().getResource("vcTableView.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("recipientLogIn.fxml"));

        Scene scene = new Scene(root,1000,1000);
        stage.setTitle("User Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}