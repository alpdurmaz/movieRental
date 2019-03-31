package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.presentation.exception.exceptions.NoTokenFoundException;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.token.TokenService;
import com.alpdurmaz.presentation.restservice.model.MovieDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//http://www.omdbapi.com/?t=MOVIETITLE&apikey=ec66d13

@RestController
public class MovieRestController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;

    @GetMapping("/api/getmovie")
    public Movie getMovieByTitle(@RequestParam String title) {

        Movie movie = movieService.getMovieByTitle(title);

        return movie;
    }

    @PostMapping("/api/insertmovie")
    public void insertMovie(@RequestParam String title){

        title = title.trim().replace(" ", "+");

        movieService.insertMovie(title);
    }

    @GetMapping("/api/movieinfo")
    public MovieDetailAPI movieInfo(@RequestParam String title){

        title = title.trim().replace(" ", "+");

        return movieService.getMovieDetail(title);
    }

    @GetMapping("/api/movielist")
    public ResponseEntity<List<Movie>> getMovies(@RequestParam String token){

        try{
            tokenService.searchToken(token);
        }catch (NoTokenFoundException e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }
}
