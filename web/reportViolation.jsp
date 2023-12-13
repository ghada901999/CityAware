<%@ page import="java.util.List" %>
<%@ page import="models.City" %>
<%@ page import="java.time.LocalDate" %>



<!DOCTYPE html>
<html>
<head>
    <title>Report Violation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
    .report-container {
        max-width: 400px;
        margin: 0 auto;
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
    }

    .report-title {
        text-align: center;
        margin-bottom: 20px;
    }

    .report-form label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    .report-form input[type="text"],
    .report-form select {
        width: 100%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 3px;
        box-sizing: border-box;
    }

    .report-form input[type="file"] {
        margin-bottom: 10px;
    }

    .report-form input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #2e59d9;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    .report-form input[type="submit"]:hover {
        background-color: #45a049;
    }

    </style>
</head>
<body>
    <div class="report-container">
        <h1 class="report-title">Report Violation</h1>
        <form class="report-form" action="ReportViolationController" method="post" enctype="multipart/form-data">
            
        
            <label for="date">Date:</label>
            <input type="text" id="date" name="date" value="<%= LocalDate.now() %>" readonly>

            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required>


            <label for="citySelect">City:</label>
            <select id="citySelect" name="city" required>
                       <% 
            List<City> cities = (List<City>) request.getSession().getAttribute("cities");
            for (City city : cities) {
            %>
                <option value="<%=city.getName()%>"><%=city.getName()%> (<%=city.getCountry()%>)</option>
            <% } %>
            </select>

            <label for="upload">Upload Picture/Video:</label>
            <input type="file" id="upload" name="upload" required>

            <label for="violationType">Violation Type:</label>
            <select id="violationType" name="violationType" required>
                <option value="">Select a violation type</option>
                <option value="RED_LIGHT_CROSSING">Red light crossing</option>
                <option value="STOP_SIGN_RUNNING">Running a stop sign</option>
                <option value="JAYWALKING">Jaywalking</option>
                <option value="LITTERING">Littering</option>
            </select>

            <input type="submit" value="Submit Report">
        </form>
    </div>



</body>
</html>
