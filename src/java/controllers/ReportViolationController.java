package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Report;
import models.ReportDAO;
import models.User;
import models.City;
import models.CityDAO;
import models.UserDAO;


@MultipartConfig(fileSizeThreshold=1024*2, // 2KB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB


public class ReportViolationController extends HttpServlet {
    
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    try {
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "123123");
        ReportDAO reportDAO = new ReportDAO(con);
         UserDAO userDAO = new UserDAO(con);
        
       
            

            String  phone = request.getParameter("phoneNumber");
            System.out.println(phone);
             User reporter = userDAO.getUserByPhoneNumber(phone);
            
            if (reporter != null) {
              
          
             System.out.println("...falg...");
            if (reporter.isBlocked()) {
               /* request.setAttribute("errorMessage", "The user " + reporter.getPhoneNumber() + " is blocked and their report has been refused.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);*/
                response.sendRedirect("error.jsp");
                return; 
            } 
            
            }
        
        
        
        
        
        
        String dateString = request.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);
       
        CityDAO cityDAO = new CityDAO(con);
        String phoneNumber = request.getParameter("phoneNumber");
        String city = request.getParameter("city");
        String country =cityDAO.getCountryByCityName(city);
        String violationType = request.getParameter("violationType");
        
        // Handle file upload
        Part part = request.getPart("upload"); // Retrieves <input type="file" name="upload">
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        
        // Set the save path to the C drive folder named Violations_images
        String savePath = "C:\\Violations_images";
        
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        String fileSavePath = savePath + File.separator + fileName;
        part.write(fileSavePath);
        String violationPath = "file://" + fileSavePath;
        
        
        Report report = new Report();
        report.setDate(date);
        report.setReporter(new User(phoneNumber,country));
        report.setCountry(country);
        report.setCity(city);
        report.setViolationType(violationType);
        report.setEvidence(violationPath);
            
        reportDAO.saveReport(report);
    
        
 
        RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
        rd.forward(request, response);
        
        }
        
     catch (ClassNotFoundException | SQLException | ParseException ex) {
        Logger.getLogger(ReportViolationController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
}