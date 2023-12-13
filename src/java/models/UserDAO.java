package models;

import java.sql.*;

public class UserDAO {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE phonenumber = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, phoneNumber);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setPhoneNumber(rs.getString("phonenumber"));
                user.setCountry(rs.getString("country"));
                user.setFalseReports(rs.getInt("falsereports"));
            }
        } catch (SQLException e) {
        }

        return user;
    }

    public void updateUser(User user) {
        try {
            String query = "UPDATE users SET  falsereports = ? WHERE phonenumber = ?";
            PreparedStatement stmt = con.prepareStatement(query);
          
            stmt.setInt(1, user.getFalseReports());
            stmt.setString(2, user.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    
    public void insertUser(User user) {
    try {
        String query = "INSERT INTO users (phonenumber, country, falsereports) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(query);
        
        stmt.setString(1, user.getPhoneNumber());
        stmt.setString(2, user.getCountry());
        stmt.setInt(3, user.getFalseReports());

        stmt.executeUpdate();
    } catch (SQLException e) {
        // log or rethrow exception as needed
    }
}

}
