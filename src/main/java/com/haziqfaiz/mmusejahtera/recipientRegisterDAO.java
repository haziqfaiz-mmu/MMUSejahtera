package com.haziqfaiz.mmusejahtera;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class recipientRegisterDAO {

    private static final String DATABASE_URL ="jdbc:sqlite:/home/haziq/Documents/javaFX/database/MMUSejahtera.db";
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