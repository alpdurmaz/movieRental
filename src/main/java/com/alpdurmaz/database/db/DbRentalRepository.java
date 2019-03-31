package com.alpdurmaz.database.db;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.rental.Rental;
import com.alpdurmaz.logic.rental.RentalRepository;
import com.alpdurmaz.database.DbConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbRentalRepository implements RentalRepository {
    private DbConnection dbConnection;

    public DbRentalRepository(DbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

   // @Override
    public void rentMovie (Customer customer, Movie movie){

        PreparedStatement preparedStatement = null;
        try {

           dbConnection.createConnection();

            preparedStatement = dbConnection.connection
                    .prepareStatement("INSERT INTO RENTALS (R_M_ID, R_C_ID, R_RENTAL_DATE, R_RETURN_DATE) values(?, ?, ?, ?)");

            preparedStatement.setInt(1, movie.getMovieID());
            preparedStatement.setInt(2, customer.getCustomerID());
            preparedStatement.setString(3, LocalDate.now().toString());
            preparedStatement.setString(4, "1900-01-01");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            dbConnection.closeConnection();
            dbConnection.closePreparedStatement(preparedStatement);
        }
    }


    public void returnRentedMovie(Customer customer, Movie returnedMovie){

        PreparedStatement preparedStatement = null;

        try {
           dbConnection.connection = DriverManager.getConnection(dbConnection.createConnectionUrl());
            preparedStatement = dbConnection.connection
                    .prepareStatement("UPDATE RENTALS SET R_RETURN_DATE=? WHERE R_M_ID=? AND R_C_ID=? AND R_RETURN_DATE=?;");

            preparedStatement.setString(1, LocalDate.now().toString());
            preparedStatement.setInt(2, returnedMovie.getMovieID());
            preparedStatement.setInt(3, customer.getCustomerID());
            preparedStatement.setString(4, "1900-01-01");
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            dbConnection.closeConnection();
            dbConnection.closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public List<Rental> getRentals(String customerName) {
        return null;
    }

    public List<Rental> getRentals(Customer customer){

        List<Rental> rentalList = new ArrayList();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            dbConnection.connection = DriverManager.getConnection(dbConnection.createConnectionUrl());
            preparedStatement = dbConnection.connection.prepareStatement("SELECT DISTINCT C_NAME, M_TITLE, R_RENTAL_DATE, R_RETURN_DATE FROM CUSTOMERS, MOVIES, RENTALS WHERE R_M_ID=M_ID AND R_C_ID=C_ID AND C_NAME=? AND R_RETURN_DATE='1900-01-01';");
            preparedStatement.setString(1, customer.getName());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Rental rental = new Rental();
                rental.setEmail(resultSet.getString("C_NAME"));
                rental.setMovieTitle(resultSet.getNString("M_TITLE"));
                rental.setRentalDate(resultSet.getString("R_RENTAL_DATE"));
                rental.setReturnDate(resultSet.getString("R_RETURN_DATE"));
                rentalList.add(rental);
            }

            return rentalList;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        finally {
            dbConnection.closeConnection();
            dbConnection.closePreparedStatement(preparedStatement);
            dbConnection.closeResultSet(resultSet);
        }

        return null;
    }

    @Override
    public void rentMovie(int customerID, int movieID) {

    }

    @Override
    public void returnRentedMovie(int userId, int movieId) {

    }

    @Override
    public void returnRentedMovie(List<Integer> list) {

    }

}