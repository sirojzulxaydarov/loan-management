package org.example.loanmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.loanmanagement.dto.CustomerDto;
import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.repository.CustomerRepository;
import org.example.loanmanagement.repository.StoreRepository;
import org.example.loanmanagement.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public CustomerDto addCustomer(CustomerDto customerDto) {
        return null;
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public List<Customer> getCustomersByStore(Integer storeId) {
        return customerRepository.findByStoreId(storeId);
    }
}
