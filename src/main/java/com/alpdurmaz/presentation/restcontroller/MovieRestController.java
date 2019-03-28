package com.alpdurmaz.presentation.restcontroller;

import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.NoObjectFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/api/getmovie")
    public Movie getMovieByTitle(@RequestParam String title) {

        Movie movie = movieService.getMovieByTitle(title);

        if (movie == null) {
            throw new NoObjectFoundException("Movie Not Found");
        }

        return movie;
    }

    @RequestMapping("/api/insertmovie")
    public void insertMovie(@RequestParam String title){

        title = title.trim().replace(" ", "+");

        movieService.insertMovie(title);
    }

    @RequestMapping("/api/movieinfo")
    public MovieDetailAPI movieInfo(@RequestParam String title){

        title = title.trim().replace(" ", "+");

        return movieService.getMovieDetail(title);
    }

    @RequestMapping("/api/movielist")
    public List<Movie> getMovies(){
        return movieService.getMovies();
    }

}
