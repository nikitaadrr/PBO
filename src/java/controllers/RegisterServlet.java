/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.User;
import models.UserDAO;
import JDBC.JDBC;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterServlet") // URL mapping untuk servlet
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(username, email, password);

        try {
            UserDAO userDAO = new UserDAO(JDBC.getConnection());
            boolean isRegistered = userDAO.registerUser(user);

            if (isRegistered) {
                response.sendRedirect("Login/Login.jsp");
            } else {
                response.sendRedirect("Register/Register.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Register/Register.jsp?error=2");
        }
    }
}
