/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author MOHAMMAD
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CityDAO {
    private Connection connection;

    public CityDAO(Connection connection) {
        this.connection = connection;
    }

    public List<City> getCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM cities");

            while (resultSet.next()) {
                City city = new City(
                    resultSet.getString("name"),
                    resultSet.getString("country"));
                    
                city.setActiveTrafficViolations(resultSet.getInt("activeTrafficViolations"));
                city.setActiveStopSignViolations(resultSet.getInt("activeStopSignViolations"));
                city.setActiveJaywalkingViolations(resultSet.getInt("activeJaywalkingViolations"));
                city.setActiveLitteringViolations(resultSet.getInt("activeLitteringViolations"));

                cities.add(city);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return cities;
    }

    public City getCityByName(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cities WHERE name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                City city = new City(
                    resultSet.getString("name"),
                    resultSet.getString("country"));
                    
                city.setActiveTrafficViolations(resultSet.getInt("activeTrafficViolations"));
                city.setActiveStopSignViolations(resultSet.getInt("activeStopSignViolations"));
                city.setActiveJaywalkingViolations(resultSet.getInt("activeJaywalkingViolations"));
                city.setActiveLitteringViolations(resultSet.getInt("activeLitteringViolations"));

                return city;
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

    public void insertCity(City city) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cities (name, country, activeTrafficViolations, activeStopSignViolations, activeJaywalkingViolations, activeLitteringViolations) VALUES (?, ?, ?, ?, ?, ?)");
            
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.setInt(3, city.getActiveTrafficViolations());
            preparedStatement.setInt(4, city.getActiveStopSignViolations());
            preparedStatement.setInt(5, city.getActiveJaywalkingViolations());
            preparedStatement.setInt(6, city.getActiveLitteringViolations());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void updateCity(City city) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE cities SET name = ?, country = ?, activeTrafficViolations = ?, activeStopSignViolations = ?, activeJaywalkingViolations = ?, activeLitteringViolations = ? WHERE name = ?");
            
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountry());
            preparedStatement.setInt(3, city.getActiveTrafficViolations());
            preparedStatement.setInt(4, city.getActiveStopSignViolations());
            preparedStatement.setInt(5, city.getActiveJaywalkingViolations());
            preparedStatement.setInt(6, city.getActiveLitteringViolations());
            preparedStatement.setString(7, city.getName());

            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void deleteCity(String name) throws SQLException {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cities WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    
       public String getCountryByCityName(String cityName) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT country FROM CITIES WHERE name = ?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, cityName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("country");
            } else {
                // Handle case where city is not found in the database. This might be an error or you might just return null.
                return null;
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) { /* ignored */ }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* ignored */ }
            }
        }
    }

}
