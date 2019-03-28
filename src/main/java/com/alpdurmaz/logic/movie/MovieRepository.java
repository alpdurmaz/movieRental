package com.alpdurmaz.logic.movie;

import com.alpdurmaz.presentation.restcontroller.MovieAPI;
import com.alpdurmaz.presentation.restcontroller.MovieDetailAPI;

import java.util.List;

public interface MovieRepository {
    Movie getMovieByTitle(String title);
    List<Movie> getMovies();
    void insertMovie(String title);
    MovieDetailAPI getMovieDetail(String title);
    void insertAllMovies(List<MovieAPI> movieAPIList);

}
