<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
            color: #333;
        }

        .error-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            text-align: center;
        }

        .error-container h1 {
            color: #f44336;
            font-size: 42px;
            margin-top: 20px;
        }

        .error-container p {
            font-size: 18px;
        }

        .error-container a {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            color: #fff;
            background-color: #4caf50;
            text-decoration: none;
            border-radius: 3px;
        }

        .error-container a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error!</h1>
        <p>The user associated with this account has been blocked from the website due to multiple violations.</p>
        <a href="home.jsp">Go to Homepage</a>
    </div>
</body>
</html>
