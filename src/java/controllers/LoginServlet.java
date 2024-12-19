package controllers; // Pastikan sesuai dengan lokasi file Anda.

import models.User;
import models.UserDAO;
import JDBC.JDBC; // Menggunakan JDBC dari package JDBC.

import javax.servlet.annotation.WebServlet; // Untuk anotasi WebServlet
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet") // URL mapping untuk LoginServlet
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ambil parameter dari form login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Buat instance UserDAO dengan koneksi database
            UserDAO userDAO = new UserDAO(JDBC.getConnection());

            // Cek validitas user
            User user = userDAO.loginUser(username, password);

            if (user != null) {
                // Jika login berhasil, set session dan redirect ke dashboard
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("Dashboard/Dashboard.jsp"); // Pastikan path benar
            } else {
                // Jika login gagal, redirect ke login.jsp dengan pesan error
                response.sendRedirect("Login/Login.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect ke halaman error jika terjadi masalah
            response.sendRedirect("Login/Login.jsp?error=2");
        }
    }
}
