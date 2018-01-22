package com.application.triptopia.Entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Person {
    private int personId;
    private int ssn;
    private boolean isManager;
    private String startDate;
    private double hourlyRate;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String address, String city, String state, Integer zipCode, int personId, int ssn, boolean isManager, String startDate, double hourlyRate) {
        super(firstName, lastName, address, city, state, zipCode);
        this.personId = personId;
        this.ssn = ssn;
        this.isManager = isManager;
        this.startDate = startDate;
        this.hourlyRate = hourlyRate;

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
