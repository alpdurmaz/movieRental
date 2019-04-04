package com.alpdurmaz.presentation.web.controller;

import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.logic.user.UserService;
import com.alpdurmaz.presentation.web.model.RentalRequest;
import com.alpdurmaz.presentation.web.model.ReturnMovieRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RentalService rentalService;

    @GetMapping("/home")
    public String home(){
        return "home";
    }

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

        int userId = userService.findByEmail(principal.getName()).getId();

        int movieId = movieService.getMovieByTitle(rentalRequest.getMovieTitle()).getMovieID();

        rentalService.rentMovie(userId, movieId);

        return "rentMovie";
    }

    @GetMapping("/return-movie")
    public String returnMovie(Principal principal, Model model){

        model.addAttribute("returnMovieRequest", new ReturnMovieRequest());
       model.addAttribute("rentalList", rentalService.getRentals(principal.getName()));

       return "returnMovie";
    }

    @PostMapping("/return-movie")
    public String returnMovie(@ModelAttribute ReturnMovieRequest returnMovieRequest){

        logger.info("LIST SIZE IS  : " + returnMovieRequest.getRentalIdList().size());

        List<Integer> rentalIdList = returnMovieRequest.getRentalIdList();

        rentalService.returnRentedMovie(rentalIdList);

        return "returnMovie";
    }


}
