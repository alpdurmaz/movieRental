package com.alpdurmaz.database.jdbc;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.rental.RentalRepository;
import com.alpdurmaz.logic.rental.Rentals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcRentalRepository implements RentalRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    /*
    @Override
    public void rentMovie(CustomerDAO customer, Movie movie) {
        String rentDate = LocalDate.now().toString();
        String returnDate = "1900-01-01";

        jdbcTemplate.update("INSERT INTO RENTALS (R_M_ID, R_C_ID, R_RENTAL_DATE, R_RETURN_DATE) VALUES(:movieID," +
                ":customerID,:rentDate,:returnDate)", new MapSqlParameterSource()
                .addValue("movieID", movie.getMovieID())
                .addValue("customerID", customer.getCustomerID())
                .addValue("rentDate", rentDate)
                .addValue("returnDate", returnDate));

    }
    */

    @Override
    public void rentMovie(int customerID, int movieID) {
        String rentDate = LocalDate.now().toString();
        String returnDate = "1900-01-01";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("movieID", movieID);
        mapSqlParameterSource.addValue("customerID", customerID);
        mapSqlParameterSource.addValue("rentDate", rentDate);
        mapSqlParameterSource.addValue("returnDate", returnDate);


        jdbcTemplate.update("INSERT INTO RENTALS (R_M_ID, R_C_ID, R_RENTAL_DATE, R_RETURN_DATE) VALUES(:movieID," +
                ":customerID,:rentDate,:returnDate)", mapSqlParameterSource);

    }

    @Override
    public void returnRentedMovie(Customer customer, Movie movie) {

        String today = LocalDate.now().toString();
        String returnDate = "1900-01-01";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("today", today);
        mapSqlParameterSource.addValue("movieId", movie.getMovieID());
        mapSqlParameterSource.addValue("customerId", customer.getCustomerID());
        mapSqlParameterSource.addValue("returnDate", returnDate);

        jdbcTemplate.update("UPDATE RENTALS SET R_RETURN_DATE=:today WHERE R_M_ID=:movieId AND R_C_ID=:customerId AND R_RETURN_DATE=:returnDate;", mapSqlParameterSource);
    }

    @Override
    public List<Rentals> getRentals(String customerName) {

        String returnDate = "1900-01-01";

        RowMapper<Rentals> rentalsRowMapper = (rs, num) -> new Rentals(rs.getString("C_NAME"),
                rs.getString("M_TITLE"), rs.getString("R_RENTAL_DATE"), rs.getString("R_RETURN_DATE"));

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("customerName", customerName);
        mapSqlParameterSource.addValue("returnDate", returnDate);

        List<Rentals> rentalsList =

                jdbcTemplate.query("SELECT DISTINCT C_NAME, M_TITLE, R_RENTAL_DATE, R_RETURN_DATE FROM CUSTOMERS, MOVIES, RENTALS " +
                        "WHERE R_M_ID=M_ID AND R_C_ID=C_ID AND C_NAME=:customerName AND R_RETURN_DATE=:returnDate", mapSqlParameterSource, rentalsRowMapper);

        return rentalsList;
    }

}
