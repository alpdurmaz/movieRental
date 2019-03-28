package com.alpdurmaz.database.jdbc;

import com.alpdurmaz.logic.customer.Customer;
import com.alpdurmaz.logic.customer.CustomerRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Customer getCustomerByName(String name) {

        RowMapper<Customer> customerRowMapper = (rs, id)-> new Customer(rs.getString("C_NAME"), rs.getInt("C_ID"));

        List<Customer> customerList = jdbcTemplate.query("SELECT * FROM CUSTOMERS WHERE C_NAME=?;", new Object[]{name}, customerRowMapper );

        Customer customer = customerList.get(0);

        return customer;
    }
}
