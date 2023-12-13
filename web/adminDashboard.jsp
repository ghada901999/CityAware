<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Report" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
        }

        .dashboard-title {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        table th {
            background-color: green;
        }

        .action-button {
            padding: 5px 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .action-button:hover {
            background-color: #45a049;
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
     <div class="dashboard-container">
        <h1 class="dashboard-title">Admin Dashboard</h1>
        <table>
            <tr>
                <th>Report ID</th>
                <th>Reporter Phone Number</th>
                <th>Date</th>
                <th>Country</th>
                <th>City</th>
                <th>Evidence Path</th>
                <th>Violation Type</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <% List<Report> reports =(List<Report>) request.getSession().getAttribute("reports"); %>
            <% for (Report report : reports) { %>
                <tr>
                    <td><%= report.getId() %></td>
                    <td><%= report.getReporter().getPhoneNumber() %></td>
                    <td><%= report.getDate() %></td>
                    <td><%= report.getCountry() %></td>
                    <td><%= report.getCity() %></td>
                    <td><%= report.getEvidencePath() %></td>
                    <td><%= report.getViolationType() %></td>
                    <td><% switch(report.isApproved()){
                        
                        case 0 : out.print("Not Approved");
                                  break;
                        case 1 : out.print("Approved");
                                 break;
                        case 2 : out.print("disapproved");
                                 break;
                    }%>
                    
                    </td>
                    <td>
                        <% if(report.isApproved() == 0){ %>
                        <form action="ReportApprovingController" method="get">
                            <input type="hidden" name="reportId" value="<%= report.getId() %>">
                            <input class="action-button" type="submit" name="submit" value="Approve">
                            <input class="action-button" type="submit" name="submit" value="Disapprove">
                        </form>
                            <% } %>
                    </td>
                </tr>
            <% } %>
        </table>
        
        <form action="ClearReportsController" method="get">
            <input class="action-button" type="submit" value="Clear All Reports">
        </form>
        
        <button class="home-button" onclick="window.location.href='home.jsp'">Go to Home Page</button>
    </div>
        
        

</body>
</html>
