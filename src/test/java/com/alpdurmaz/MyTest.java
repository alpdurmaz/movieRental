package com.alpdurmaz;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertTrue;

public class MyTest {

    @Test
    public void name() {
        assertTrue(new BCryptPasswordEncoder().matches("12345","$2a$10$dbvIBEMq.sC9Id6XbS6CvevL3JnGtl9/ugpRliAW5dt5UAgxCgHiO"));
    }
}