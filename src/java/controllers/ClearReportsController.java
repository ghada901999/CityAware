package controllers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.ReportDAO;

public class ClearReportsController extends HttpServlet {

    private Connection con;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "123123");
            
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClearReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ReportDAO reportDAO = new ReportDAO(con);
        try {
            reportDAO.clearReports();
            request.setAttribute("message", "All reports have been successfully deleted.");
        } catch (SQLException e) {
            request.setAttribute("error", "An error occurred while deleting reports.");
        }

       
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminController");
        requestDispatcher.forward(request, response);
    }


}
