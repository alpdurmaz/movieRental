package com.alpdurmaz.logic.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByName(String name){

        Customer customer = customerRepository.getCustomerByName(name);

        return customer;
    }
}
