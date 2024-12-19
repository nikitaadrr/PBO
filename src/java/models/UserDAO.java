/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import JDBC.JDBC; // Import JDBC.java dari package JDBC
import java.sql.*;

public class UserDAO {
    private Connection connection;

    // Constructor menerima koneksi database
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean registerUser(User user) {
        Connection connection = null;
        try {
            connection = JDBC.getConnection(); // Menggunakan JDBC.java untuk koneksi
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBC.closeConnection(connection); // Menutup koneksi menggunakan JDBC.java
        }
    }

    public User loginUser(String username, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = JDBC.getConnection(); // Menggunakan JDBC.java untuk koneksi
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection(connection); // Menutup koneksi menggunakan JDBC.java
        }
        return user;
    }
}
