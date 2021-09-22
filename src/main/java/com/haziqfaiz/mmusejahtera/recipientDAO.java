package com.haziqfaiz.mmusejahtera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class recipientDAO {

    private static final String DATABASE_URL ="jdbc:sqlite:/home/haziq/Documents/javaFX/database/MMUSejahtera.db";
    private static final String SELECT_QUERY = "SELECT * FROM RECIPIENT WHERE RECIPIENT_ID = ? AND RECIPIENT_PASSWORD = ?";
    private static final String INSERT_QUERY = "INSERT INTO RECIPIENT (RECIPIENT_ID, RECIPIENT_PASSWORD, RECIPIENT_NAME) VALUES (?,?,?)";

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
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
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