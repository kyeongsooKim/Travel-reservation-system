package com.application.triptopia.Entity;

public class Flight {
    String airlineId;
    int flightNo;
    int noOfSeats;
    String daysOperating;
    int maxLengthOfStay;
    int minLengthOfStay;

    public Flight() {
    }

    public Flight(String airlineId, int flightNo, int noOfSeats, String daysOperating, int maxLengthOfStay, int minLengthOfStay) {
        this.airlineId = airlineId;
        this.flightNo = flightNo;
        this.noOfSeats = noOfSeats;
        this.daysOperating = daysOperating;
        this.maxLengthOfStay = maxLengthOfStay;
        this.minLengthOfStay = minLengthOfStay;
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

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getDaysOperating() {
        return daysOperating;
    }

    public void setDaysOperating(String daysOperating) {
        this.daysOperating = daysOperating;
    }

    public int getMaxLengthOfStay() {
        return maxLengthOfStay;
    }

    public void setMaxLengthOfStay(int maxLengthOfStay) {
        this.maxLengthOfStay = maxLengthOfStay;
    }

    public int getMinLengthOfStay() {
        return minLengthOfStay;
    }

    public void setMinLengthOfStay(int minLengthOfStay) {
        this.minLengthOfStay = minLengthOfStay;
    }
}
