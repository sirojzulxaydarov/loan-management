package org.example.loanmanagement.repository;

import org.example.loanmanagement.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebtRepository extends JpaRepository<Debt, Integer> {
  List<Debt> findByCustomerId(Integer customer_id);
  List<Debt> findByIsPaid(Boolean isPaid);
}