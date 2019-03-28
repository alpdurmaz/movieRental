package com.alpdurmaz;

import com.alpdurmaz.presentation.Display;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SpringMovieApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private Display display;

    public static void main(String[] args) {
        SpringApplication.run(SpringMovieApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        display.displayMain();
    }
}
