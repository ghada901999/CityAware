/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author MOHAMMAD
 */
public class City {
    private String name;
    private String country;
    private int activeTrafficViolations;
    private int activeStopSignViolations;
    private int activeJaywalkingViolations;
    private int activeLitteringViolations;

   
    public City(String name, String country) {
        this.name = name;
        this.country = country;
        this.activeTrafficViolations = 0;
        this.activeStopSignViolations = 0;
        this.activeJaywalkingViolations = 0;
        this.activeLitteringViolations = 0;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getActiveTrafficViolations() {
        return activeTrafficViolations;
    }

    public void setActiveTrafficViolations(int activeTrafficViolations) {
        this.activeTrafficViolations = activeTrafficViolations;
    }

    public int getActiveStopSignViolations() {
        return activeStopSignViolations;
    }

    public void setActiveStopSignViolations(int activeStopSignViolations) {
        this.activeStopSignViolations = activeStopSignViolations;
    }

    public int getActiveJaywalkingViolations() {
        return activeJaywalkingViolations;
    }

    public void setActiveJaywalkingViolations(int activeJaywalkingViolations) {
        this.activeJaywalkingViolations = activeJaywalkingViolations;
    }

    public int getActiveLitteringViolations() {
        return activeLitteringViolations;
    }

    public void setActiveLitteringViolations(int activeLitteringViolations) {
        this.activeLitteringViolations = activeLitteringViolations;
    }

   
    public boolean isDangerous() {
        return activeTrafficViolations >= 2 || activeStopSignViolations >= 2;
    }

    public boolean isDirty() {
        return activeLitteringViolations >= 2;
    }

    public boolean isInsane() {
        return activeJaywalkingViolations >= 2;
    }
}
