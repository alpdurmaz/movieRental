package com.alpdurmaz.logic.movie;

import com.alpdurmaz.logic.NoObjectFoundException;
import com.alpdurmaz.presentation.restcontroller.MovieAPI;
import com.alpdurmaz.presentation.restcontroller.MovieDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    public Movie getMovieByTitle(String title){

        Movie movie = movieRepository.getMovieByTitle(title);

        if(movie == null){
            throw new NoObjectFoundException("Movie Not Found");
        }

        return movie;
    }

    public void insertMovie(String title){
        movieRepository.insertMovie(title);
    }

    public MovieDetailAPI getMovieDetail(String title){
       return movieRepository.getMovieDetail(title);
    }

    /*
    public void insertAllMovies(String filePath){

        ObjectMapper objectMapper = new ObjectMapper();

        List<MovieAPI> movieAPIList = objectMapper.readValue(new File(filePath), new TypeReference<>())



    }
    */

    public List<Movie> getMovies(){

        List<Movie> movieList = movieRepository.getMovies();

        if(movieList.size() == 0){
            throw new NoObjectFoundException("There Are No Movies At List");
        }

        return movieRepository.getMovies();
    }



}
