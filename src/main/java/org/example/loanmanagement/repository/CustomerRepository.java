package org.example.loanmanagement.repository;

import org.example.loanmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByStoreId(Integer storeId);
}