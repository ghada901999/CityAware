<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Report" %>
<%@ page import="models.Admin" %>
<!DOCTYPE html>
<html>
<head>
    <title>Process Report</title>
</head>
<body>
    <h1>Process Report</h1>
    <% String reportId = request.getParameter("reportId"); %>
    <% Report report = Admin.getReportById(reportId); %>
    <% if (report != null) { %>
        <% if (request.getParameter("approve") != null) { %>
            <% Admin.approveReport(report); %>
            <p>Report approved successfully!</p>
        <% } else if (request.getParameter("disapprove") != null) { %>
            <% Admin.disapproveReport(report); %>
            <p>Report disapproved successfully!</p>
        <% } %>
    <% } %>
</body>
</html>
