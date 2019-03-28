package com.alpdurmaz.database.db;

import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieRepository;
import com.alpdurmaz.presentation.restcontroller.MovieAPI;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbMovieRepository implements MovieRepository {
    private com.alpdurmaz.database.DbConnection dbConnection;

    public DbMovieRepository(com.alpdurmaz.database.DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Movie getMovieByTitle(String movieTitle) {

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection.createConnection();
            preparedStatement = dbConnection.connection.prepareStatement("SELECT M_ID, M_TITLE FROM MOVIES where M_TITLE = ?");
            preparedStatement.setString(1, movieTitle);
            resultSet = preparedStatement.executeQuery();

            Movie movie = getMovieFromDataBase(resultSet);

            return movie;

        } catch (SQLException e) {
            throw new RuntimeException("Something Went wrong", e);
        }
        finally {
            dbConnection.closeConnection();
            dbConnection.closePreparedStatement(preparedStatement);
            dbConnection.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Movie> getMovies(){

        ResultSet resultSet = null;

        try{
            dbConnection.createConnection();
            resultSet = dbConnection.connection.createStatement().executeQuery("SELECT * FROM MOVIES;");
            List<Movie> movieList = putMoviesToList(resultSet);

            return movieList;
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

        finally {
            dbConnection.closeConnection();
            dbConnection.closeResultSet(resultSet);
        }

        return null;
    }

    @Override
    public void insertMovie(MovieAPI movieAPI) {

    }

    @Override
    public void insertAllMovies(List<MovieAPI> movieAPIList) {

    }

    private List<Movie> putMoviesToList(ResultSet resultSet){

        List<Movie> movieList = new ArrayList<>();

        try{
            while(resultSet.next()){
                Movie movie = new Movie();
                movie.setMovieID(resultSet.getInt("M_ID"));
                movie.setTitle(resultSet.getString("M_TITLE"));
                movie.setMainActor(resultSet.getString("M_MAIN_ACTOR"));
                movie.setReleasedYear(resultSet.getInt("M_YEAR"));
                movie.setGenre(resultSet.getString("M_GENRE"));
                movie.setPrice(resultSet.getInt("M_PRICE"));
                movieList.add(movie);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return movieList;
    }

    private Movie getMovieFromDataBase(ResultSet resultSet) {
        Movie movie = new Movie();

        try {
            while (resultSet.next()) {
                movie.setMovieID(resultSet.getInt("M_ID"));
                movie.setTitle(resultSet.getString("M_TITLE"));
            }
        }

        catch (SQLException ex){
            ex.printStackTrace();
        }

        return movie;
    }
}

