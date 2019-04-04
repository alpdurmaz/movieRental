package com.alpdurmaz.database;

import com.alpdurmaz.logic.exception.exceptions.MovieListUpdateException;
import com.alpdurmaz.logic.exception.exceptions.MovieNotFoundException;
import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieRepository;
import com.alpdurmaz.presentation.exceptions.RestServiceMovieSearchException;
import com.alpdurmaz.presentation.restservice.models.restmodels.MovieAPI;
import com.alpdurmaz.presentation.restservice.models.restmodels.MovieDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcMovieRepository implements MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Movie getMovieByTitle(String title) {

        RowMapper<Movie> movieRowMapper =
                (rs, i)-> new Movie(rs.getInt(1)
                        ,rs.getString(2)
                        ,rs.getString(3)
                        ,rs.getInt(4)
                        ,rs.getString(5)
                        ,rs.getInt(6));

        String query = "SELECT * FROM MOVIES WHERE M_TITLE=?;";

        try {

            return jdbcTemplate.queryForObject(query, new Object[]{title}, movieRowMapper);

        } catch (DataAccessException dae){

            throw new MovieNotFoundException("Movie Not Found", dae);
        }
    }

    @Override
    public void insertMovie(String title) {

        MovieAPI movieAPI = new MovieAPI();

        try {
            movieAPI = restTemplate.getForObject("http://www.omdbapi.com/?t=" + title + "&apikey=ec66d13", MovieAPI.class);
        }
        catch (RestClientException rce){
            throw new RestServiceMovieSearchException("Movie Not Found in Rest Service!", rce);
        }

        int year = Integer.parseInt(movieAPI.getYear());
        int price = 5;

        jdbcTemplate.update("INSERT INTO MOVIES (M_TITLE, M_MAIN_ACTOR, M_YEAR, M_GENRE, M_PRICE) VALUES(?, ?, ?, ?, ?)",
                new Object[]{movieAPI.getTitle(), movieAPI.getActors(), year, movieAPI.getGenre(), price});
    }

    @Override
    public MovieDetailAPI getMovieDetail(String title) {

        MovieDetailAPI movieDetailAPI = new MovieDetailAPI();

        try {
            restTemplate.getForObject("http://www.omdbapi.com/?t=" + title + "&apikey=ec66d13", MovieDetailAPI.class);
        }
        catch (RestClientException rce){
            throw new RestServiceMovieSearchException("Movie Not Found in Rest Service!", rce);
        }

        return movieDetailAPI;
    }

    @Override
    public void insertAllMovies(List<MovieAPI> movieAPIList){

        String sqlCommand = "INSERT INTO MOVIES (M_TITLE, M_MAIN_ACTOR, M_YEAR, M_GENRE, M_PRICE) VALUES(?, ?, ?, ?, ?)";

        try {

            jdbcTemplate.batchUpdate(sqlCommand, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    MovieAPI movieAPI = movieAPIList.get(i);
                    ps.setString(1, movieAPI.getTitle());
                    ps.setString(2, movieAPI.getActors());
                    ps.setString(3, movieAPI.getYear());
                    ps.setString(4, movieAPI.getGenre());
                    ps.setInt(5, 5);
                }

                @Override
                public int getBatchSize() {
                    return movieAPIList.size();
                }
            });
        }catch (DataAccessException dae){
            throw new MovieListUpdateException("Movie List Could Not Updated!", dae);
        }

    }

    @Override
    public List<Movie> getMovies() {

        RowMapper<Movie> movieRowMapper = (rs, num) -> new Movie(rs.getInt("M_ID")
                , rs.getString("M_TITLE")
                , rs.getString("M_MAIN_ACTOR")
                , rs.getInt("M_YEAR")
                , rs.getString("M_GENRE")
                , rs.getInt("M_PRICE"));

        try {
            return jdbcTemplate.query("SELECT * FROM MOVIES", movieRowMapper);
        }
        catch (DataAccessException dae){
            throw new MovieNotFoundException("Movie List is Empty!", dae);
        }

    }
}
