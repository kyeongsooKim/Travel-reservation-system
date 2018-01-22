package com.application.triptopia.Entity;

public class Ticket {
    private int accountNo;
    private String airlineId;
    private int flightNo;
    private String seatClass;
    private String seatNo;
    private String meal;
    private int legNo;

    public Ticket() {
    }

    public Ticket(int accountNo, String airlineId, int flightNo, String seatClass, String seatNo, String meal, int legNo) {
        this.accountNo = accountNo;
        this.airlineId = airlineId;
        this.flightNo = flightNo;
        this.seatClass = seatClass;
        this.seatNo = seatNo;
        this.meal = meal;
        this.legNo = legNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(String airlineId) {
        this.airlineId = airlineId;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getLegNo() {
        return legNo;
    }

    public void setLegNo(int legNo) {
        this.legNo = legNo;
    }
}
