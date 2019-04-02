package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.presentation.restservice.models.restmodels.Rental;
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
    public void rentMovie(@RequestBody Rental rental){

        rentalService.rentMovie(rental.getCustomerID(), rental.getMovieID());
    }
}
