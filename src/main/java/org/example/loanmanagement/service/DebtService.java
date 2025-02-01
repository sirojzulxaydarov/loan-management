package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Debt;
import org.example.loanmanagement.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtService {
    @Autowired
    private DebtRepository debtRepository;

    public List<Debt> findAll() {
        return debtRepository.findAll();
    }

    public List<Debt> findByCustomer(Integer id){
        return debtRepository.findByCustomerId(id);
    }

    public Debt createDebt(Debt debt){
        return debtRepository.save(debt);
    }

    public Debt getDebtById(Integer id) {
        return debtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Debt not found with id " + id));
    }

    public Debt updateDebt(Integer id, Debt debtDetails){
        Debt debt = getDebtById(id);
        debt.setAmount(debtDetails.getAmount());
        debt.setDate(debtDetails.getDate());
        debt.setDescription(debtDetails.getDescription());
        return debtRepository.save(debt);

    }

    public void deleteDebt(Integer id){
        debtRepository.deleteById(id);
    }
}
