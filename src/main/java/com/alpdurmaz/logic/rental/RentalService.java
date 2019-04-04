package com.alpdurmaz.logic.rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public void rentMovie(int userId, int movieId) {

        rentalRepository.rentMovie(userId, movieId);
    }

    public void returnRentedMovie(int userId, int movieId) {
        rentalRepository.returnRentedMovie(userId, movieId);
    }

    public void returnRentedMovie(List<Integer> list) {
        rentalRepository.returnRentedMovie(list);
    }

    public List<Rental> getRentals(String customerName) {
        return rentalRepository.getRentals(customerName);
    }
}
