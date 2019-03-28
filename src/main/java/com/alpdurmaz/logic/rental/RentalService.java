package com.alpdurmaz.logic.rental;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.presentation.restcontroller.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    /*
    public void rentMovie(CustomerDAO customer, Movie movie){
        rentalRepository.rentMovie(customer, movie);
    }
    */

    public void rentMovie(Rental rental){
        rentalRepository.rentMovie(rental.getCustomerID(), rental.getMovieID());
    }

    public void returnRentedMovie(Customer customer, Movie movie){
        rentalRepository.returnRentedMovie(customer, movie);
    }

    public List<Rentals> getRentals(String customerName){
        return rentalRepository.getRentals(customerName);
    }
}
