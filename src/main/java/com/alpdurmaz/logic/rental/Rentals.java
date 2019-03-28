package com.alpdurmaz.logic.rental;

public class Rentals {
    private String customerName;
    private String movieTitle;
    private String rentalDate;
    private String returnDate;

    public Rentals(String customerName, String movieTitle, String rentalDate, String returnDate) {
        this.customerName = customerName;
        this.movieTitle = movieTitle;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public Rentals() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Rentals{" +
                "customerName='" + customerName + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
