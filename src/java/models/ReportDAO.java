/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author MOHAMMAD
 */

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;
import java.util.*;

public class ReportDAO {
    private Connection connection;

    public ReportDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Report> getReports() throws SQLException {
        List<Report> reports = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM reports");

            while (resultSet.next()) {
                User reporter = new User(resultSet.getString("reporter_phone"),resultSet.getString("country"));
                      
                Date date = new Date(resultSet.getDate("reportdate").getTime());

                Report report = new Report(
                    resultSet.getInt("id"),
                    reporter,
                    date,
                    resultSet.getString("country"),
                    resultSet.getString("city"),
                    resultSet.getString("evidencePath"),
                    resultSet.getString("violationType"));
                report.setApproved(resultSet.getInt("isApproved"));

                reports.add(report);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return reports;
    }

private User getUser(String phoneNumber) throws SQLException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE phoneNumber = ?");
        preparedStatement.setString(1, phoneNumber);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User user = new User(
                resultSet.getString("phoneNumber"),
                resultSet.getString("country")); 
                
            user.setFalseReports(resultSet.getInt("falseReports"));

            return user;
        } else {
            return null;
        }
    } finally {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

public void saveReport(Report report) throws SQLException {
    PreparedStatement preparedStatement = null;
    System.out.println("Flag");
    try {
        String sql = "INSERT INTO reports (id, reportDate, reporter_phone, country, city, violationType, evidencePath, isApproved) VALUES (report_seq.nextval,?, ?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);

        // Assuming the reporter_phone field in your database is a VARCHAR type that stores the phone number of the User who reported the issue.
        preparedStatement.setString(2, report.getReporter().getPhoneNumber());

        // SQL date is needed to be converted from java.util.Date
        java.sql.Date sqlDate = new java.sql.Date(report.getDate().getTime());
        preparedStatement.setDate(1, sqlDate);

        preparedStatement.setString(3, report.getCountry());
        preparedStatement.setString(4, report.getCity());
        preparedStatement.setString(6, report.getEvidencePath());

        // Assuming the violation_type field in your database is a VARCHAR type that stores the name of the ViolationType enum.
        preparedStatement.setString(5, report.getViolationType());

        preparedStatement.setInt(7, 0);

        preparedStatement.executeUpdate();
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

   
       public void updateReport(Report report) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "UPDATE reports SET reporter_phone = ?, reportdate = ?, country = ?, city = ?, evidencePath = ?, violationtype = ?, isApproved = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Assuming the reporter_phone field in your database is a VARCHAR type that stores the phone number of the User who reported the issue.
            preparedStatement.setString(1, report.getReporter().getPhoneNumber());

            // SQL date is needed to be converted from java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(report.getDate().getTime());
            preparedStatement.setDate(2, sqlDate);

            preparedStatement.setString(3, report.getCountry());
            preparedStatement.setString(4, report.getCity());
            preparedStatement.setString(5, report.getEvidencePath());

            // Assuming the violation_type field in your database is a VARCHAR type that stores the name of the ViolationType enum.
            preparedStatement.setString(6, report.getViolationType());

            preparedStatement.setInt(7, report.isApproved());
            
            preparedStatement.setInt(8, report.getId());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
}
    
       
       
    public Report getReportById(int reportId) throws SQLException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        preparedStatement = connection.prepareStatement("SELECT * FROM reports WHERE id = ?");
        preparedStatement.setInt(1, reportId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            User reporter = new User(resultSet.getString("reporter_phone"),resultSet.getString("country")); // Assuming reporter_id corresponds to a user in the user table.
            Date date = new Date(resultSet.getDate("reportdate").getTime());

            Report report = new Report(
                resultSet.getInt("id"),
                reporter,
                date,
                resultSet.getString("country"),
                resultSet.getString("city"),
                resultSet.getString("evidencePath"),
                resultSet.getString("violationType"));
            report.setApproved(resultSet.getInt("isApproved"));

            return report;
        } else {
            return null;
        }
    } finally {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}

    
    
    public void deleteReport(int reportId) throws SQLException {
    PreparedStatement preparedStatement = null;
    try {
        String sql = "DELETE FROM reports WHERE id = ?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, reportId);
        preparedStatement.executeUpdate();
    } finally {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
       
}
    
    
    public void clearReports() throws SQLException {
    Statement statement = null;
    try {
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM reports");
    } finally {
        if (statement != null) {
            statement.close();
        }
    }
}


}

