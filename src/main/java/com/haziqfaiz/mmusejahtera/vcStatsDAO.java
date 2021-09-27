package com.haziqfaiz.mmusejahtera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class vcStatsDAO {

    private static final String DATABASE_URL ="jdbc:sqlite:/home/haziq/Documents/javaFX/database/MMUSejahtera.db";
    private static final String SELECT_ALL = "SELECT * FROM VC_STATS";

    public void buildData(TableView<VCSTATS> table) throws ClassNotFoundException, SQLException {

        ResultSet resultSet = null;

        //TableView<Recipient> table = new TableView<>();
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            //System.out.println(resultSet.next());
            System.out.println(resultSet.getMetaData().getColumnName(1));
            //System.out.println(resultSet.getMetaData().getColumnCount());
            int columnsNumber = resultSet.getMetaData().getColumnCount();

            //Giving readable names to columns
            for(int i=0 ; i<resultSet.getMetaData().getColumnCount()-1; i++) {
                TableColumn column = new TableColumn<>();
                column.setText(resultSet.getMetaData().getColumnName(i+2));
                column.setCellValueFactory(new PropertyValueFactory<Recipient, String>(resultSet.getMetaData().getColumnName(i+2))); //Setting cell property value to correct variable from Person class.
                table.getColumns().add(column);
            }
            ObservableList dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));
            table.setItems(dbData);
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

    }


    //extracting data from ResultSet to ArrayList
    private ArrayList dataBaseArrayList(ResultSet resultSet) throws SQLException {
        ArrayList<VCSTATS> data =  new ArrayList<>();
        while (resultSet.next()) {
            Recipient r = new Recipient();
            VCSTATS v = new VCSTATS();
            v.setName(resultSet.getString("VC_NAME"));
            v.setDate(resultSet.getString("DATE"));
            v.setAppointment(resultSet.getString("APOINTMENT"));
            v.setFirstDose(resultSet.getString("FIRST_DOSE"));
            v.setSecondDose(resultSet.getString("SECOND_DOSE"));
            v.setCapacity(resultSet.getString("CAPACITY"));

            data.add(v);
        }
        //System.out.println(data.get(1).getID());
        return data;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
