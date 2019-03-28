package com.alpdurmaz.presentation.web;


import com.alpdurmaz.logic.customer.CustomerService;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.presentation.restcontroller.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RentalService rentalService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }

    @GetMapping("/list-all-movies")
    public String getAllMovies(Model model){

        List<Movie> movieList = movieService.getMovies();

        model.addAttribute("movieList", movieList);

        return "listAllMovies";
    }

    @GetMapping("/rent-movie")
    public String rentMovie(Model model, Principal principal){
        model.addAttribute("rentalRequest", new RentalRequest());
        model.addAttribute("username", principal.getName());

        return "rentMovie";
    }

    @PostMapping("/rent-movie")
    public String rentMovie(@ModelAttribute RentalRequest rentalRequest, Principal principal){

        int customerId = customerService.getCustomerByName(principal.getName()).getCustomerID();
        int movieId = movieService.getMovieByTitle(rentalRequest.getMovieTitle()).getMovieID();

        Rental rental = new Rental(customerId, movieId);

        rentalService.rentMovie(rental);

        return "rentMovie";
    }

    @GetMapping("/return-movie")
    public String returnMovie(Principal principal, Model model){

       model.addAttribute("rentalList", rentalService.getRentals(principal.getName()));

       return "returnMovie";
    }
}
