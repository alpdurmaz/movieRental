package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.presentation.restservice.models.restmodels.RentalRequestRestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalRestController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/rest/rentals")
    public List<com.alpdurmaz.logic.rental.Rental> getRentals (@RequestParam String customerName){

       return rentalService.getRentals(customerName);
    }

    @PostMapping("/rest/rentmovie")
    public String rentMovie(@RequestBody RentalRequestRestObject rentalRequestRestObject){

        rentalService.rentMovie(rentalRequestRestObject.getCustomerID(), rentalRequestRestObject.getMovieID());

        return "CustomerId " + rentalRequestRestObject.getCustomerID() + " Rented " + rentalRequestRestObject.getMovieID() + " Successfully";
    }

    @PostMapping("/rest/returnmovie")
    public String returnRentedMovie(@RequestBody RentalRequestRestObject rentalRequestRestObject){

        rentalService.returnRentedMovie(rentalRequestRestObject.getCustomerID(), rentalRequestRestObject.getMovieID());

        return "CustomerId " + rentalRequestRestObject.getCustomerID() + " Returned " + rentalRequestRestObject.getMovieID() + " Successfully";
    }
}
