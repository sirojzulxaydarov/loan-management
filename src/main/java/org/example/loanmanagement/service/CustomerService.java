package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.entity.User;
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

    public void saveCustomer(Customer customer) {

        // Customerni storeId si null bo'lmasligini oldini olish
        if (customer.getStore() == null || customer.getStore().getId() == null) {
            throw new IllegalArgumentException("Store id is null");
        }
        // Customerni store sini topib olish storega tegishli user orqali
        Optional<Store> storeOptional = storeRepository.findByUserId(customer.getStore().getUser().getId());
        if (storeOptional.isEmpty()) {
            throw new IllegalArgumentException("Store not found");
        }
        customer.setStore(storeOptional.get());
        customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    public void updateCustomer(Integer customerId,Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();

            //Yangilanish
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
            customerToUpdate.setStore(customer.getStore());
            customerRepository.save(customerToUpdate);
        }else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

}
