/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author MOHAMMAD
 */
public class User {
    private String phoneNumber;
    private String country;
    private int falseReports;

    // Constructor
    public User(String phoneNumber, String country) {
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.falseReports = 0;
    }

    User() {
        
    }

    // Getters and setters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFalseReports() {
        return falseReports;
    }

    public void setFalseReports(int falseReports) {
        this.falseReports = falseReports;
    }
    
     public void incrementFalseReports() {
        this.falseReports +=1;
         System.out.println(falseReports);

    }
 public boolean isBlocked() {
    return falseReports > 3;
}

}

