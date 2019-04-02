package com.alpdurmaz.logic.movie;

import com.alpdurmaz.presentation.restservice.models.restmodels.MovieAPI;
import com.alpdurmaz.presentation.restservice.models.restmodels.MovieDetailAPI;

import java.util.List;

public interface MovieRepository {
    Movie getMovieByTitle(String title);
    List<Movie> getMovies();
    void insertMovie(String title);
    MovieDetailAPI getMovieDetail(String title);
    void insertAllMovies(List<MovieAPI> movieAPIList);

}
