<%-- 
    Document   : Dashboard
    Created on : Dec 19, 2024, 2:14:13 PM
    Author     : Yunita
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Drive Ez</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<div class="container mt-5">
    <div class="text-center">
        <h1>Welcome, <%= user.getUsername() %>!</h1>
        <p>Email: <%= user.getEmail() %></p>
        <a href="logout.jsp" class="btn btn-danger">Logout</a>
    </div>
</div>
</body>
</html>
