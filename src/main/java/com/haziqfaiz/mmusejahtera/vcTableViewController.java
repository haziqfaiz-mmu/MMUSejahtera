package com.haziqfaiz.mmusejahtera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;

public class vcTableViewController {

    @FXML
    private Button back_button;

    @FXML
    private TableView<VCSTATS> table;

    @FXML
    private TableColumn<VCSTATS, String> nameColumn;

    @FXML
    private TableColumn<VCSTATS, String> dateColumn;

    @FXML
    private TableColumn<VCSTATS, String> capacityColumn;

    @FXML
    private TableColumn<VCSTATS, String> firstDoseColumn;

    @FXML
    private TableColumn<VCSTATS, String> secondDoseColumn;

    @FXML
    private TableColumn<VCSTATS, String> appointmentColumn;

    ResultSet resultSet = null;

    public void mohScene(){
        System.out.println("Back to register");
    }
}
