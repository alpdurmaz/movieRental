package com.alpdurmaz.logic.rental;

import com.alpdurmaz.presentation.web.model.ReturnMovieRequest;

import java.util.List;

public interface RentalRepository {
    void rentMovie(int userId, int movieId);
    void returnRentedMovie(int userId, int movieId);
    void returnRentedMovie(List<Integer>list);
    List<Rental> getRentals(String customerName);
}
