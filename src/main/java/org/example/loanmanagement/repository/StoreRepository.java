package org.example.loanmanagement.repository;

import org.example.loanmanagement.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByUserId(Integer userId);
}