package com.alpdurmaz.logic.rental;

public class Rental {
    private String email;
    private String movieTitle;
    private String rentalDate;
    private String returnDate;
    private int rentalId;

    public Rental(String email, String movieTitle, String rentalDate, String returnDate, int rentalId) {
        this.email = email;
        this.movieTitle = movieTitle;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.rentalId = rentalId;
    }

    public Rental() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "email='" + email + '\'' +
                ", movieTitle='" + movieTitle + '\'' +
                ", rentalDate='" + rentalDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
