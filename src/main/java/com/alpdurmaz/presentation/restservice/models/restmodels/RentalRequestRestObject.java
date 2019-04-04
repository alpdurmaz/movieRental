package com.alpdurmaz.presentation.restservice.models.restmodels;

public class RentalRequestRestObject {

    private int customerID;
    private int movieID;

    public RentalRequestRestObject(int customerID, int movieID) {
        this.customerID = customerID;
        this.movieID = movieID;
    }

    public RentalRequestRestObject() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
