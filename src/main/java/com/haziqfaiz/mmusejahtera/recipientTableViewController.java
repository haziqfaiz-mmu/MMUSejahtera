package com.haziqfaiz.mmusejahtera;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.ResultSet;


public class recipientTableViewController {

    @FXML
    private Button back_button;

    @FXML
    private TableView<Recipient> table;

    @FXML
    private TableColumn<Recipient, String> idColumn;

    @FXML
    private TableColumn<Recipient, String> nameColumn;

    @FXML
    private TableColumn<Recipient, String> firstDateColumn;

    @FXML
    private TableColumn<Recipient, String> firstStatusColumn;

    @FXML
    private TableColumn<Recipient, String> secondDateColumn;

    @FXML
    private TableColumn<Recipient, String> secondStatusColumn;

    ResultSet resultSet = null;



    public void mohScene(){
        System.out.println("Back to register");
    }
}
