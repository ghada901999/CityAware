<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submission Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            padding: 20px;
        }
        .success-message {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
        }
        p {
            color: #666;
        }
        .home-button {
            margin-top: 20px;
            background-color: #2e59d9;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="success-message">
        <h1>Success!</h1>
        <p>Your violation report has been submitted successfully.</p>
        <button class="home-button" onclick="window.location.href='home.jsp'">Go to Home Page</button>
    </div>

    <%@ include file="reportViolation.jsp" %>
</body>
</html>
