package com.alpdurmaz.presentation.restservice.controller;

import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.presentation.restservice.models.restmodels.MovieDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//http://www.omdbapi.com/?t=MOVIETITLE&apikey=ec66d13

@RestController
public class MovieRestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;

    @GetMapping("/rest/getmovie")
    public Movie getMovieByTitle(@RequestParam String title) {

        Movie movie = movieService.getMovieByTitle(title);

        return movie;
    }

    @PostMapping("/rest/insertmovie")
    public void insertMovie(@RequestParam String title) {

        movieService.insertMovie(title);
    }

    @GetMapping("/rest/movieinfo")
    public MovieDetailAPI movieInfo(@RequestParam String title) {

        title = title.trim().replace(" ", "+");

        return movieService.getMovieDetail(title);
    }

    @GetMapping("/rest/movielist")
    public ResponseEntity<List<Movie>> getMovies() {

        return new ResponseEntity<>(movieService.getMovies(), HttpStatus.OK);
    }
}
