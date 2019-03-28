package com.alpdurmaz.presentation.web;


import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.customer.CustomerService;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.logic.rental.Rentals;
import com.alpdurmaz.presentation.restcontroller.MovieAPI;
import com.alpdurmaz.presentation.restcontroller.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;


public class DisplayWebController {

    @Autowired
    MovieService movieService;

    @Autowired
    RentalService rentalService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/main")
    public String main(Model model) {
       // model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("main", new MovieAPI());

        return "main";
    }

    @PostMapping("/main")
    public String mainpost(@ModelAttribute MovieAPI movieAPI){
        return "result";
    }


    @GetMapping("/listallmovies")
    public String listallmovies(Model model){

        model.addAttribute("movies", movieService.getMovies());

        return "listallmovies";
    }

    @GetMapping("/rentamovie")
    public String rentamovie(Model model){
        model.addAttribute("rentalRequest", new RentalRequest());
        return "rentamovie";
    }

    @PostMapping("/rentamovie")
    public String rentamovie(@ModelAttribute RentalRequest rentalRequest, Principal principal){

       int customerId =  customerService.getCustomerByName(principal.getName()).getCustomerID();
       int movieId = movieService.getMovieByTitle(rentalRequest.getMovieTitle()).getMovieID();
       Rental rental = new Rental(customerId, movieId);
       rentalService.rentMovie(rental);

        return "result";
    }

    @GetMapping("/returnmovie")
    public String returnMovie(Model model, Principal principal){

        model.addAttribute("rentalRequest", new RentalRequest());
        model.addAttribute("username", principal.getName());

        return "returnMovie";
    }

    @PostMapping("/returnmovie")
    public String returnMovie(@ModelAttribute RentalRequest rentalRequest, Principal principal){

        Customer customer = customerService.getCustomerByName(principal.getName());
        Movie movie = movieService.getMovieByTitle(rentalRequest.getMovieTitle());

        rentalService.returnRentedMovie(customer,movie);

        return "result";

    }

    @GetMapping("/list-customer-rentals")
    public String listCustomerRentals(String customerName, Model model){

        List<Rentals> rentalsList = rentalService.getRentals(customerName);

        model.addAttribute("rentals", rentalsList);

        return "listCustomerRentals";
    }


    @PostMapping("/list-customer-rentals")
    public String listCustomerRentals(@ModelAttribute String customerName){

        rentalService.getRentals(customerName);

        return "listCustomerRentals";
    }


    @GetMapping
    public String result(){
        return "result";
    }

}
