package controllers;

import models.Report;
import models.User;
import models.City;
import models.ReportDAO;
import models.UserDAO;
import models.CityDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class ReportApprovingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "123123");

            ReportDAO reportDAO = new ReportDAO(con);
            UserDAO userDAO = new UserDAO(con);
            CityDAO cityDAO = new CityDAO(con);

            String reportIdString = request.getParameter("reportId");
            int reportId = Integer.parseInt(reportIdString);

            Report report = reportDAO.getReportById(reportId);
            User reporter = userDAO.getUserByPhoneNumber(report.getReporter().getPhoneNumber());
            if (reporter == null) {
                // If reporter is not found, create a new User object
                reporter = new User(report.getReporter().getPhoneNumber(), report.getReporter().getCountry());
            }
             
            if (reporter.isBlocked()) {
                request.setAttribute("errorMessage", "The user " + reporter.getPhoneNumber() + " is blocked and their report has been refused.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else {
                String action = request.getParameter("submit");

                // Check if the report is expired (more than 1 month old)
                LocalDate reportDate = report.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                Period period = Period.between(reportDate, LocalDate.now());
                if (period.getMonths() > 1) {
                    request.setAttribute("errorMessage", "The report with ID " + reportId + " is expired and cannot be approved or disapproved.");
                    request.getRequestDispatcher("/expired.jsp").forward(request, response);
                } else {
                    if ("Approve".equals(action)) {
                        report.setApproved(1);
                        reportDAO.updateReport(report);

                        City city = cityDAO.getCityByName(report.getCity());
                        switch (report.getViolationType()) {
                            case "RED_LIGHT_CROSSING":
                                city.setActiveTrafficViolations(city.getActiveTrafficViolations()+1);
                                break;
                            case "STOP_SIGN_RUNNING":
                                city.setActiveStopSignViolations( city.getActiveStopSignViolations()+1);
                                break;
                            case "JAYWALKING":
                                city.setActiveJaywalkingViolations(city.getActiveJaywalkingViolations()+1);
                                break;
                             case "LITTERING":
                                city.setActiveStopSignViolations( city.getActiveStopSignViolations()+1);
                                break;    
                            default:
                                city.setActiveLitteringViolations(city.getActiveLitteringViolations()+1);
                                break;
                        }
                        cityDAO.updateCity(city);
                        //reportDAO.deleteReport(report.getId());
                         // reportDAO.updateReport(report);
                    } else if ("Disapprove".equals(action)) {
                        report.setApproved(2);
                        User isUserInDatabase = userDAO.getUserByPhoneNumber(report.getReporter().getPhoneNumber());
                        if(isUserInDatabase ==null) userDAO.insertUser(reporter);
                        reportDAO.updateReport(report);
                        
                        
                        reporter.incrementFalseReports();
                        
                        
                      //   System.out.println("Before increment, False reports: " + reporter.getFalseReports()); // debug statement
                        userDAO.updateUser(reporter);
                    }

                     RequestDispatcher dispatcher = request.getRequestDispatcher("AdminController");
                                  dispatcher.forward(request, response);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
