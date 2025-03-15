package org.example.loanmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.loanmanagement.dto.CustomerDto;
import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.addCustomer(customerDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Customer>> getCustomerByStore(@PathVariable Integer storeId) {
        return ResponseEntity.ok(customerService.getCustomersByStore(storeId));
    }




}
