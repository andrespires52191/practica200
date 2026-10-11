package org.example;

import org.example.utils.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        // Cerrar xampp automaticamente
        //xampp_start();

        try {
            DatabaseConnection db = DatabaseConnection.getInstance();
            Connection connection = db.getConnection();
            System.out.println("Programa iniciado.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Cerrar xampp automaticamente
        //xampp_stop();

        System.out.println("Programa terminado.");
    }

    private static void xampp_start() {
        try {
            System.out.println("Lanzando xampp");
            ProcessBuilder xampp_start = new ProcessBuilder("C:\\xampp\\xampp_start.exe");
            xampp_start.start().waitFor();
            System.out.println("Xampp iniciado");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void xampp_stop() {
        try {
            System.out.println("Deteniendo xampp");
            ProcessBuilder xampp_stop = new ProcessBuilder("C:\\xampp\\xampp_stop.exe");
            xampp_stop.start().waitFor();
            System.out.println("Xampp detenido");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
