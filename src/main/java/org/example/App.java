package org.example;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        // Iniciar Xampp automáticamente
        //xampp_start();

        // Cerrar Xampp automáticamente
        //xampp_stop();

        System.out.println("Los tests deben lanzarse con JUnit");
    }

    private static void xampp_start() {
        try {
            System.out.println("Iniciando xampp");
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
