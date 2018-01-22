package com.application.triptopia.Entity;

public class Customer extends Person{

    private int personId;
    private String creditCardNo;
    private String phoneNo;
    private String email;
    private String creationDate;
    private int rating;

    public Customer() {
    }

    public Customer(int personId, String creditCardNo, String phoneNo, String email, String creationDate, int rating) {
        this.personId = personId;
        this.creditCardNo = creditCardNo;
        this.phoneNo = phoneNo;
        this.email = email;
        this.creationDate = creationDate;
        this.rating = rating;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
