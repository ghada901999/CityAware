
 
 
package controllers;

import models.City;
import models.CityDAO;
import models.User;
import models.UserDAO;
import models.Report;
import models.ReportDAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;



/*
public class DatabaseInitializer extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DatabaseUtil.createTables();
        
        // Add initial cities
        try (Connection connection = DatabaseUtil.getConnection()) {
            CityDAO cityDAO = new CityDAO(connection);
            cityDAO.insertCity(new City("New York", "United States"));
            cityDAO.insertCity(new City("Tokyo", "Japan"));
            cityDAO.insertCity(new City("London", "United Kingdom"));
            cityDAO.insertCity(new City("Sydney", "Australia"));
            cityDAO.insertCity(new City("Paris", "France"));
        }  catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

*/

/*
package controllers;

import models.City;
import models.CityDAO;
import models.User;
import models.UserDAO;
import models.Report;
import models.ReportDAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import java.util.logging.Level;
import java.util.logging.Logger;
*/
public class DatabaseInitializer extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // System.out.println("Initializing database...");
        DatabaseUtil.createTables();
        
        // Add initial cities
        try (Connection connection = DatabaseUtil.getConnection()) {
            CityDAO cityDAO = new CityDAO(connection);
            cityDAO.insertCity(new City("New York", "United States"));
            cityDAO.insertCity(new City("Tokyo", "Japan"));
            cityDAO.insertCity(new City("London", "United Kingdom"));
            cityDAO.insertCity(new City("Sydney", "Australia"));
            cityDAO.insertCity(new City("Paris", "France"));

            // Create a UserDAO for user-related operations
            UserDAO userDAO = new UserDAO(connection);

            // Create a user with 4 false reports
            User user1 = new User("0790963251", "United States");
            user1.setFalseReports(4);
            userDAO.insertUser(user1);
 
            
            User user2 = new User("0790963250", "United Kingdom");
            user2.setFalseReports(1);
            userDAO.insertUser(user2);
            
            
            
            // Create a ReportDAO for report-related operations
            ReportDAO reportDAO = new ReportDAO(connection);

            // Create two reports from 2 months ago
            LocalDateTime twoMonthsAgo = LocalDateTime.now().minusMonths(2);
            Date dateTwoMonthsAgo = Date.from(twoMonthsAgo.atZone(ZoneId.systemDefault()).toInstant());

           
            
              Report report1 = new Report();
              report1.setDate(dateTwoMonthsAgo);
              report1.setReporter(user2);
              report1.setCountry("Japan");
              report1.setCity("Tokyo");
              report1.setViolationType("STOP_SIGN_RUNNING");
              report1.setEvidence("c://");

              
              
              Report report2 = new Report();
              report2.setDate(dateTwoMonthsAgo);
              report2.setReporter(user2);
              report2.setCountry("United States");
              report2.setCity("New York");
              report2.setViolationType("RED_LIGHT_CROSSING");
              report2.setEvidence("c://");
              
             
            reportDAO.saveReport(report1);
            reportDAO.saveReport(report2);

            // Update cities with violation data
            City newYork = cityDAO.getCityByName("New York");
            newYork.setActiveTrafficViolations(newYork.getActiveTrafficViolations() + 5);
            cityDAO.updateCity(newYork);

            City tokyo = cityDAO.getCityByName("Tokyo");
            tokyo.setActiveStopSignViolations(tokyo.getActiveStopSignViolations() + 7);
            cityDAO.updateCity(tokyo);

            // More city updates can be added here as per your requirements

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DatabaseInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println("Database initialized.");
    }
}
