package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Customer not found")
        );
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}
