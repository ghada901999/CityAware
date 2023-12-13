<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.City" %>
<%@ page import="java.util.List" %>
<%@ page import="controllers.CityStateController"%>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .banner {
            background-color: #007bff;
            color: #fff;
            padding: 20px 0;
            text-align: center;
            margin-bottom: 20px;
        }

        .description {
            text-align: center;
            padding: 20px;
        }

        .text-center {
            text-align: center;
        }

        .container {
            max-width: 960px;
            margin: 0 auto;
            padding: 20px;
        }

        .btn {
            color: #fff;
            background-color: #007bff;
            border: none;
            padding: 10px 20px;
            text-decoration: none;
            cursor: pointer;
            display: inline-block;
        }

        select {
            width: 100%;
            padding: 10px;
        }

    </style>
</head>
<body>

<div class="banner">
    <h1>Welcome to CityAware</h1>
</div>

<div class="description">
    <p>Your go-to platform for city-specific information and violation reporting. Be aware, be safe!</p>
</div>

<div class="text-center mt-3 mb-5">
    <a href="admin_login.jsp" class="btn">Admin Login</a>
    <a href="reportViolation.jsp" class="btn">Report a Violation</a>
</div>

<div class="container">
    <h2 class="text-center mt-5">Choose a City</h2>
    
    <form action="CityStateController" method="get">
        <select name="cityName" class="mt-4">
            <% 
            List<City> cities = (List<City>) request.getSession().getAttribute("cities");
            for (City city : cities) {
            %>
                <option value="<%=city.getName()%>"><%=city.getName()%> (<%=city.getCountry()%>)</option>
            <% } %>
        </select>

        <input type="submit" value="Show City State" class="btn mt-4">
    </form>

</div>

</body>
</html>
