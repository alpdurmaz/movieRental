package com.alpdurmaz.presentation;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.customer.CustomerService;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieService;
import com.alpdurmaz.logic.rental.RentalService;
import com.alpdurmaz.logic.rental.Rentals;
import com.alpdurmaz.presentation.restcontroller.MovieAPI;
import com.alpdurmaz.presentation.restcontroller.MovieDetailAPI;
import com.alpdurmaz.presentation.restcontroller.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import org.springframework.web.client.RestTemplate;

@Component
public class Display {
    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RentalService rentalService;

    public void displayMain() {

        boolean shouldContinue = true;

        while (shouldContinue) {
            System.out.println("Enter Your Choose Below");
            System.out.println("1. List All Movies Available to Rent");
            System.out.println("2. Rent a Movie");
            System.out.println("3. Return a Movie");
            System.out.println("4. Insert Movie to List");
            System.out.println("5. Quit");

            switch (getMenuInput()) {
                case 1:
                    listAllMovies();
                    getMovieInfo();
                    break;
                case 2:
                    rentMovie();
                    break;
                case 3:
                    returnMovie();
                    break;
                case 4:
                    insertMovie();
                    break;
                case 5:
                    shouldContinue = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
                    System.out.println("Please Enter Between 1-4");
            }
        }
    }

    private void listAllMovies() {

        System.out.println("****************************");
        System.out.println("1 - MOVIE LIST");

        List<Movie> list = movieService.getMovies();
        displayList(list);
    }

    private void getMovieInfo(){

        System.out.println("****************************");
        System.out.println("Enter Movie Name to Get More Details or Type Return For Other Options");
        String movieTitle = getKeyboardInput().trim().replace(" ", "+").toLowerCase();

        if(movieTitle.equalsIgnoreCase("return")){
            return;
        }

        MovieDetailAPI movieDetailAPI = restTemplate.getForObject("http://www.omdbapi.com/?t=" + movieTitle + "&apikey=ec66d13", MovieDetailAPI.class);
        System.out.println(movieDetailAPI.toString());
    }

    private void rentMovie() {
        System.out.println("****************************");
        System.out.println("2 - RENT A MOVIE");
        System.out.println("Please Insert Your Name to Rent A Movie");

        String customerName = getKeyboardInput();
        Customer customer = customerService.getCustomerByName(customerName);

        System.out.println("Please Insert Movie Title to Rent");
        String movieTitle = getKeyboardInput();
        Movie movie = movieService.getMovieByTitle(movieTitle);

        Rental rental = new Rental(customer.getCustomerID(), movie.getMovieID());

        rentalService.rentMovie(rental);
    }

    private void returnMovie() {
        System.out.println("****************************");
        System.out.println("3 - RETURN MOVIE");
        System.out.println("Please Insert Your Name to Display Rentals");

        String customerName = getKeyboardInput();
        Customer customer = customerService.getCustomerByName(customerName);
        List<Rentals>rentalsList = rentalService.getRentals(customerName);

        displayList(rentalsList);

        System.out.println("Please Insert Movie Title You Want to Return");
        String movieTitle = getKeyboardInput();
        Movie movie = movieService.getMovieByTitle(movieTitle);
        rentalService.returnRentedMovie(customer, movie);
        
        rentalsList = rentalService.getRentals(customerName);

        displayList(rentalsList);

    }

    private void insertMovie(){
        System.out.println("****************************");
        System.out.println("4 - INSERT MOVIE");

        String movieTitleParameter = getKeyboardInput().toLowerCase().trim().replace(" ", "+");

        MovieAPI movieAPI = restTemplate.getForObject("http://www.omdbapi.com/?t=" + movieTitleParameter + "&apikey=ec66d13", MovieAPI.class);

        movieService.insertMovie(movieAPI);

    }

    private void insertAllMovies(){
        System.out.println("****************************");
        System.out.println("4 - INSERT ALL MOVIEs");
        System.out.println("Insert JSON FILE PATH");

        String filePath = getKeyboardInput();



    }

    private <E>void displayList(List<E> list){

        for (E element : list){
            System.out.println(element.toString());
        }
    }

    private int getMenuInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    private String getKeyboardInput() {
        return scanner.nextLine();
    }
}
