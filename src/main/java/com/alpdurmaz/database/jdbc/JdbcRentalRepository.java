package com.alpdurmaz.database.jdbc;

import com.alpdurmaz.logic.exception.exceptions.MovieRentFailedException;
import com.alpdurmaz.logic.exception.exceptions.MovieReturnFailedException;
import com.alpdurmaz.logic.exception.exceptions.RentalListException;
import com.alpdurmaz.logic.rental.RentalRepository;
import com.alpdurmaz.logic.rental.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcRentalRepository implements RentalRepository {

    @Autowired
    private NamedParameterJdbcTemplate namParJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void rentMovie(int userId, int movieId) {
        String rentDate = LocalDate.now().toString();
        String returnDate = "1900-01-01";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("movieID", movieId);
        mapSqlParameterSource.addValue("customerID", userId);
        mapSqlParameterSource.addValue("rentDate", rentDate);
        mapSqlParameterSource.addValue("returnDate", returnDate);

        try {
            namParJdbcTemplate.update("INSERT INTO RENTALS (R_M_ID, R_C_ID, R_RENTAL_DATE, R_RETURN_DATE) VALUES(:movieID," +
                    ":customerID,:rentDate,:returnDate)", mapSqlParameterSource);
        }
        catch (DataAccessException dae){
            throw new MovieRentFailedException("Movie Rent Failed!", dae);
        }
    }

    @Override
    public void returnRentedMovie(int userId, int movieId) {

        String today = LocalDate.now().toString();
        String returnDate = "1900-01-01";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("today", today);
        mapSqlParameterSource.addValue("movieId", movieId);
        mapSqlParameterSource.addValue("customerId", userId);
        mapSqlParameterSource.addValue("returnDate", returnDate);

        try {
            namParJdbcTemplate
                    .update("UPDATE RENTALS SET R_RETURN_DATE=:today WHERE R_M_ID=:movieId AND" +
                            " R_C_ID=:customerId AND R_RETURN_DATE=:returnDate;", mapSqlParameterSource);
        }catch (DataAccessException dae){
            throw new MovieReturnFailedException("Movie Return Operation Failed!", dae);
        }
    }

    @Override
    public void returnRentedMovie(List<Integer> list) {

        String today = LocalDate.now().toString();

        String sqlCommand = "UPDATE RENTALS SET R_RETURN_DATE=? WHERE RENTAL_ID=?;";

        try {

            jdbcTemplate.batchUpdate(sqlCommand, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {

                    ps.setString(1, today);
                    ps.setInt(2, list.get(i).intValue());
                }

                @Override
                public int getBatchSize() {
                    return list.size();
                }
            });
        }catch (DataAccessException dae){
            throw new MovieReturnFailedException("Movie Return Operation Failed!", dae);
        }
    }

    @Override
    public List<Rental> getRentals(String customerName) {

        String returnDate = "1900-01-01";

        RowMapper<Rental> rentalsRowMapper = (rs, num) -> new Rental(rs.getString("email"),
                rs.getString("M_TITLE"), rs.getString("R_RENTAL_DATE"), rs.getString("R_RETURN_DATE"), rs.getInt("RENTAL_ID"));

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("customerName", customerName);
        mapSqlParameterSource.addValue("returnDate", returnDate);

        try {
            return namParJdbcTemplate
                    .query("SELECT email, M_TITLE, R_RENTAL_DATE, R_RETURN_DATE, RENTAL_ID FROM user, MOVIES, RENTALS " +
                                    "WHERE R_M_ID=M_ID AND R_C_ID=user_id AND email=:customerName AND R_RETURN_DATE=:returnDate",
                            mapSqlParameterSource, rentalsRowMapper);
        }catch (DataAccessException dae){
            throw new RentalListException("Rental List Not Found", dae);
        }
    }
}
