package org.example;

import org.example.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class App 
{
    public static void main( String[] args )
    {
        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection connection = db.getConnection();
            System.out.println("Programa iniciado.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Programa terminado.");
    }
}
