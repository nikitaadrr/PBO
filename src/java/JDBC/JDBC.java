/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/DriveEz"; // Ganti sesuai database Anda
    private static final String USERNAME = "root"; // Ganti sesuai username database Anda
    private static final String PASSWORD = "";    // Ganti sesuai password database Anda

    // Method untuk mendapatkan koneksi database
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load driver MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Buat koneksi ke database
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Koneksi ke database berhasil!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Koneksi ke database gagal: " + e.getMessage());
        }
        return connection;
    }

    // Method untuk menutup koneksi database
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi database ditutup.");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database: " + e.getMessage());
            }
        }
    }
}
