package com.haziqfaiz.mmusejahtera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class vcTableViewController {

    @FXML
    private Button back_button;

    @FXML
    private TableView<VCSTATS> table;

    /////////////////////////
    public void initialize ()throws SQLException, ClassNotFoundException{

        vcStatsDAO dao = new vcStatsDAO();
        dao.buildData(table);
    }


    ///////////////////////////////
    public void mohScene(){
        System.out.println("Back to MOH");
    }


}
