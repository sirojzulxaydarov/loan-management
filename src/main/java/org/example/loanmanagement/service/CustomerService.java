package org.example.loanmanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.repository.CustomerRepository;
import org.example.loanmanagement.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;

    private final StoreRepository storeRepository;




}
