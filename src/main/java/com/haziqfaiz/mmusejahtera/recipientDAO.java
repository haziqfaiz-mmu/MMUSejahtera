package com.haziqfaiz.mmusejahtera;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class recipientDAO {

    private static final String DATABASE_URL ="jdbc:sqlite:/home/haziq/Documents/javaFX/database/MMUSejahtera.db";
    private static final String SELECT_QUERY = "SELECT * FROM RECIPIENT WHERE RECIPIENT_ID = ? AND RECIPIENT_PASSWORD = ?";
    private static final String INSERT_QUERY = "INSERT INTO RECIPIENT (RECIPIENT_ID, RECIPIENT_PASSWORD, RECIPIENT_NAME) VALUES (?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM RECIPIENT";

    public void insertRecord(String icPassport, String password, String name) throws SQLException{

        try(Connection connection = DriverManager.getConnection(DATABASE_URL)){

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1,icPassport);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3,name);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            printSQLException(e);
        }
    }

    public boolean validate(String ic, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, ic);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getMetaData().getColumnName(8));
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    //return a tableview filled with resultset
    public void buildData(TableView<Recipient> table) throws ClassNotFoundException, SQLException {

        ResultSet resultSet = null;

        //TableView<Recipient> table = new TableView<>();
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {

            System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();
            //System.out.println(resultSet.next());
            System.out.println(resultSet.getMetaData().getColumnName(2));
            //System.out.println(resultSet.getMetaData().getColumnCount());
            int columnsNumber = resultSet.getMetaData().getColumnCount();

            //Giving readable names to columns
            for(int i=0 ; i<resultSet.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                column.setText(resultSet.getMetaData().getColumnName(i+1));
                column.setCellValueFactory(new PropertyValueFactory<Recipient, String>(resultSet.getMetaData().getColumnName(i+1))); //Setting cell property value to correct variable from Person class.
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
        ArrayList<Recipient> data =  new ArrayList<>();
        while (resultSet.next()) {
            Recipient r = new Recipient();
            r.setId(resultSet.getString("RECIPIENT_ID"));
            r.setPassword(resultSet.getString("RECIPIENT_PASSWORD"));System.out.println(resultSet.getString("RECIPIENT_PASSWORD"));
            r.setName(resultSet.getString("RECIPIENT_NAME"));System.out.println(resultSet.getString("RECIPIENT_NAME"));
            r.setFirstDoseDate(resultSet.getString("FIRST_DOSE_DATE"));
            r.setFirstDoseStatus(resultSet.getString("FIRST_DOSE_STATUS"));
            r.setSecondDoseDate(resultSet.getString("SECOND_DOSE_DATE"));
            r.setSecondDoseStatus(resultSet.getString("SECOND_DOSE_STATUS"));
            r.setVc(resultSet.getString("VC_NAME"));

            data.add(r);
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