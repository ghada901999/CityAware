/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author MOHAMMAD
 */
import java.util.Date;


public class Report {
    private int id;
    private User reporter;
    private Date date; 
    private String country;
    private String city;
    private String evidencePath;
    private String violationType;
    private int isApproved;

   
    public Report(int id, User reporter, Date date, String country, String city, String evidencePath, String violationType) {
        this.id = id;
        this.reporter = reporter;
        this.date = date;
        this.country = country;
        this.city = city;
        this.evidencePath = evidencePath;
        this.violationType = violationType;
        this.isApproved = 0;
    }

    public Report() {
       isApproved=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
    public String getEvidencePath() {
        return evidencePath;
    }

    public void setEvidence(String evidencePath) {
        this.evidencePath = evidencePath;
    }

    
    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    
    public int isApproved() {
        return isApproved;
    }

    public void setApproved(int isApproved) {
        this.isApproved = isApproved;
    }
}

