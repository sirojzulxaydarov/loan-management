package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.entity.User;
import org.example.loanmanagement.repository.CustomerRepository;
import org.example.loanmanagement.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
