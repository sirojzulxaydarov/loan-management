package org.example.loanmanagement.service;

import org.example.loanmanagement.entity.Payment;
import org.example.loanmanagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByIdForDebt(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + id));
    }

    public Payment updatePayment(Integer id, Payment paymentDetails) {
        Payment payment = getPaymentByIdForDebt(id);
        payment.setAmount(paymentDetails.getAmount());
        payment.setPaymentDate(paymentDetails.getPaymentDate());
        return paymentRepository.save(payment);
    }

    public void deletePaymentById(Integer id) {
        paymentRepository.deleteById(id);
    }

    public List<Payment> getPaymentsByDebtId(Integer debtId) {
        return paymentRepository.findAll().stream()
                .filter(payment -> payment.getDebt().getId().equals(debtId))
                .toList();
    }


}
