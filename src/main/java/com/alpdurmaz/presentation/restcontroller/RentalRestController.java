package com.alpdurmaz.presentation.restcontroller;

import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.logic.rental.Rentals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalRestController {

    @Autowired
    private RentalService rentalService;

    @RequestMapping("/api/rentals")
    public List<Rentals> getRentals (@RequestParam String customerName){
       return rentalService.getRentals(customerName);
    }

    @PostMapping("/api/rentmovie")
    public void rentMovie(@RequestBody Rental rental){
        rentalService.rentMovie(rental);
    }
}
