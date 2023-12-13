/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

/**
 *
 * @author MOHAMMAD
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {
    
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "SYSTEM";
    private static final String DB_PASSWORD = "123123";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void createTables() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();) {
            
            String createUsersTable = "CREATE TABLE users (phoneNumber VARCHAR(20) PRIMARY KEY, country VARCHAR(50), falseReports INT)";
            statement.execute(createUsersTable);

            String createReportsTable = "CREATE TABLE reports (id NUMBER PRIMARY KEY, reporter_phone VARCHAR2(20), reportDate DATE, country VARCHAR2(50), city VARCHAR2(50), evidencePath VARCHAR2(200), violationType VARCHAR2(50), isApproved NUMBER(1))";
            statement.execute(createReportsTable);

            String createCitiesTable = "CREATE TABLE cities (name VARCHAR(50) PRIMARY KEY, country VARCHAR(50), activeTrafficViolations INT, activeStopSignViolations INT, activeJaywalkingViolations INT, activeLitteringViolations INT)";
            statement.execute(createCitiesTable);
            
            String createSequence="CREATE SEQUENCE report_seq START WITH 1 INCREMENT BY 1;";
            statement.execute(createSequence);

        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

