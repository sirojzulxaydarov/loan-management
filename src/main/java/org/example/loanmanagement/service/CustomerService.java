package org.example.loanmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.loanmanagement.dto.CustomerDto;
import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.entity.Store;
import org.example.loanmanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreService storeService;

    public Customer addCustomer(CustomerDto customerDto) {

        Store currentStore = storeService.getCurrentStore();
        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .phoneNumber(customerDto.getPhoneNumber())
                .address(customerDto.getAddress())
                .store(currentStore)
                .build();
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomersByStore(Integer storeId) {
        return customerRepository.findByStoreId(storeId);
    }
}
