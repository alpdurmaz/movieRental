package com.alpdurmaz.database.jdbc;

import com.alpdurmaz.logic.movie.Movie;
import com.alpdurmaz.logic.movie.MovieRepository;
import com.alpdurmaz.presentation.restcontroller.MovieAPI;
import com.alpdurmaz.presentation.restcontroller.MovieDetailAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
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

        RowMapper<Movie> movieRowMapper = (rs, num) -> new Movie(rs.getInt("M_ID"), rs.getString("M_TITLE"));
        List<Movie> movieList = jdbcTemplate.query("SELECT * FROM MOVIES WHERE M_TITLE=?;", new Object[]{title}, movieRowMapper);

        Movie movie = movieList.get(0);

        return movie;
    }

    @Override
    public void insertMovie(String title) {

        MovieAPI movieAPI = restTemplate.getForObject("http://www.omdbapi.com/?t="+ title + "&apikey=ec66d13", MovieAPI.class);

        int year = Integer.parseInt(movieAPI.getYear());
        int price = 5;

        jdbcTemplate.update("INSERT INTO MOVIES (M_TITLE, M_MAIN_ACTOR, M_YEAR, M_GENRE, M_PRICE) VALUES(?, ?, ?, ?, ?)",
                new Object[]{movieAPI.getTitle(), movieAPI.getActors(), year, movieAPI.getGenre(), price});
    }

    @Override
    public MovieDetailAPI getMovieDetail(String title) {

        MovieDetailAPI movieDetailAPI = restTemplate.getForObject("http://www.omdbapi.com/?t="+ title + "&apikey=ec66d13", MovieDetailAPI.class);

        return movieDetailAPI;
    }

    @Override
    public void insertAllMovies(List<MovieAPI> movieAPIList){

        String sqlCommand = "INSERT INTO MOVIES (M_TITLE, M_MAIN_ACTOR, M_YEAR, M_GENRE, M_PRICE) VALUES(?, ?, ?, ?, ?)";

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
    }

    @Override
    public List<Movie> getMovies() {

        RowMapper<Movie> movieRowMapper = (rs, num) -> new Movie(rs.getInt("M_ID")
                , rs.getString("M_TITLE")
                , rs.getString("M_MAIN_ACTOR")
                , rs.getInt("M_YEAR")
                , rs.getString("M_GENRE")
                , rs.getInt("M_PRICE"));

        return jdbcTemplate.query("SELECT * FROM MOVIES", movieRowMapper);
    }
}
