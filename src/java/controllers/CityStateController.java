/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.City;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/getCityState")
public class CityStateController extends HttpServlet {
    // Database connection parameters
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "SYSTEM";
    private static final String DB_PASSWORD = "123123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("cityName");
        
        // Establish the database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Prepare the SQL statement
            String sql = "SELECT * FROM cities WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cityName);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Get the city state from the result set
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                int activeTrafficViolations = resultSet.getInt("activeTrafficViolations");
                int activeStopSignViolations = resultSet.getInt("activeStopSignViolations");
                int activeJaywalkingViolations = resultSet.getInt("activeJaywalkingViolations");
                int activeLitteringViolations = resultSet.getInt("activeLitteringViolations");

                 // Create and return the City object
                    City CityState= new City(name, country);
                    CityState.setActiveTrafficViolations(activeTrafficViolations);
                    CityState.setActiveStopSignViolations(activeStopSignViolations);
                    CityState.setActiveJaywalkingViolations(activeJaywalkingViolations);
                    CityState.setActiveLitteringViolations(activeLitteringViolations);
                // Set city state as attribute in request
                req.setAttribute("cityState", CityState);
                
                // Forward to JSP page to display city state
                req.getRequestDispatcher("cityState.jsp").forward(req, resp);
            } else {
                // City not found
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
