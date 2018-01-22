package com.application.triptopia.Entity;

public class SalesReport {
    private String resrDate;
    private double totalFare;
    private double bookingFee;

    public SalesReport() {
    }

    public SalesReport(String resrDate, double totalFare, double bookingFee) {
        this.setResrDate(resrDate);
        this.setTotalFare(totalFare);
        this.setBookingFee(bookingFee);
    }

    public String getResrDate() {
        return resrDate;
    }

    public void setResrDate(String resrDate) {
        this.resrDate = resrDate;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(double bookingFee) {
        this.bookingFee = bookingFee;
    }
}
