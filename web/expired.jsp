<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Report Expired</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
            color: #333;
        }

        .expired-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        .expired-container h1 {
            color: #f44336;
            font-size: 42px;
            margin-top: 20px;
        }

        .expired-container p {
            font-size: 18px;
        }

        .expired-container a {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            color: #fff;
            background-color: #4caf50;
            text-decoration: none;
            border-radius: 3px;
        }

        .expired-container a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="expired-container">
        <h1>Report Expired!</h1>
        <p><%= request.getAttribute("errorMessage") %></p>
        <a href="adminDashboard.jsp">Return to Dashboard</a>
    </div>
</body>
</html>
