package com.alpdurmaz.database;

import com.alpdurmaz.presentation.exceptions.NoTokenFoundException;
import com.alpdurmaz.logic.user.User;
import com.alpdurmaz.logic.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JdbcTokenRepository implements TokenRepository {

    @Autowired
    private NamedParameterJdbcTemplate namParJdbcTemplate;

    @Override
    public String saveToken(int userId) {

        String token = UUID.randomUUID().toString();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("t_user_id", userId);
        mapSqlParameterSource.addValue("t_token", token);

        namParJdbcTemplate.update("INSERT INTO tokens VALUES(:t_user_id, :t_token)",mapSqlParameterSource);

        return token;
    }

    @Override
    public String getToken(User user) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("t_user_id", user.getId());

        String query = "SELECT t_token FROM tokens WHERE t_user_id=:t_user_id";

        try{
            return namParJdbcTemplate.queryForObject(query,mapSqlParameterSource, String.class);
        }catch (DataAccessException e){
            throw new NoTokenFoundException("No Token Found For This User", e);
        }
    }

    @Override
    public String searchToken(String tokenInput) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("t_token", tokenInput);

        String query = "SELECT t_token FROM tokens WHERE t_token=:t_token";

        try {
            return namParJdbcTemplate
                    .queryForObject(query, mapSqlParameterSource, String.class);
        }catch (DataAccessException e){
            throw new NoTokenFoundException("Invalid Token!", e);
        }
    }
}
