package org.example.loanmanagement.repository;

import org.example.loanmanagement.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}