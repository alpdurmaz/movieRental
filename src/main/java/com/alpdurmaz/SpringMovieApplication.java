package com.alpdurmaz;

import com.alpdurmaz.presentation.console.ConsoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringMovieApplication extends SpringBootServletInitializer  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ConsoleView consoleView;

    public static void main(String[] args) {
        SpringApplication.run(SpringMovieApplication.class, args);
    }

}
