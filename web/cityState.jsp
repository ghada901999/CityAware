58559<%@ include file="home.jsp" %>
<%@ page import="models.City" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>City State</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .city-info {
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
        }
        .city-info p {
            margin: 0;
            padding: 5px 0;
        }
    </style>
</head>
<body>
    <h1>City State</h1>
    
    <%-- Retrieve the city object from the request --%>
    <%
        City city = (City)request.getAttribute("cityState");
    %>
    
    <%-- Display the city state --%>
    <% if (city != null) { %>
        <div class="city-info">
            <p><strong>City:</strong> <%= city.getName() %></p>
            <p><strong>Country:</strong> <%= city.getCountry() %></p>
            
            <%-- Conditionally display city safety status --%>
            <% if (city.isDangerous()) { %>
                <p>The city is considered dangerous due to high traffic and stop sign violations.</p>
            <% } else { %>
                <p>The city is considered safe in terms of traffic and stop sign violations.</p>
            <% } %>
            
            <%-- Conditionally display city cleanliness status --%>
            <% if (city.isDirty()) { %>
                <p>The city is considered dirty due to high littering violations.</p>
            <% } else { %>
                <p>The city is considered clean in terms of littering violations.</p>
            <% } %>

            <%-- Conditionally display city sanity status --%>
            <% if (city.isInsane()) { %>
                <p>The people in the city are considered insane due to high jaywalking violations.</p>
            <% } else { %>
                <p>The people in the city are considered sane in terms of jaywalking violations.</p>
            <% } %>

        </div>
    <% } else { %>
        <p>City not found.</p>
    <% } %>
</body>
</html>
