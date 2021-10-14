package com.haziqfaiz.mmusejahtera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class recipientTableViewController {

    @FXML
    private Button back_button;

    @FXML
    private TableView<Recipient> table;


    /////////////////////////
    public void initialize ()throws SQLException, ClassNotFoundException{

        recipientDAO dao = new recipientDAO();
        dao.buildData(table);
    }


    ///////////////////////////////
    public void mohScene(){
        System.out.println("Back to MOH Main View");
    }
}



