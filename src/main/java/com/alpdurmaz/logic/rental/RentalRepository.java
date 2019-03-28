package com.alpdurmaz.logic.rental;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.movie.Movie;

import java.util.List;

public interface RentalRepository {
    //void rentMovie(CustomerDAO customer, Movie movie);
    void rentMovie(int customerID, int movieID);
    void returnRentedMovie(Customer customer, Movie movie);
    List<Rentals> getRentals(String customerName);
}
