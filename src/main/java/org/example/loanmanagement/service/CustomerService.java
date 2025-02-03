package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.repository.CustomerRepository;
import org.example.loanmanagement.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        if (customer.getStore() == null || customer.getStore().getId() == null) {
            throw new RuntimeException("Customer can not be null");
        }

        Optional<Store> storeOptional = storeRepository.findById(customer.getStore().getId());

        if (storeOptional.isEmpty()) {
            throw new RuntimeException("Store can not be null");
        }

        customer.setStore(storeOptional.get());
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer customer) {
        //User tanlagan cutomer ni topish
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isEmpty()) {
            throw new RuntimeException("Customer can not be null");
        }
        //Topilgan customerni malumotlarini ko'chirish
        Customer existingCustomer = existingCustomerOptional.get();

        // Yangilash
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());

        if (customer.getStore() != null && customer.getStore().getId() != null) {

            Optional<Store> storeOptional = storeRepository.findById(customer.getStore().getId());
            if (storeOptional.isEmpty()) {
                throw new RuntimeException("Store can not be null");
            }
            existingCustomer.setStore(storeOptional.get());
        }

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

}
