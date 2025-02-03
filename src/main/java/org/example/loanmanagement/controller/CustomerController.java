package org.example.loanmanagement.controller;

import org.example.loanmanagement.entity.Customer;
import org.example.loanmanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "listCustomers";
    }

    @GetMapping("/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {

        try {
            customerService.addCustomer(customer);
            return "redirect:/api/customers/";
        }catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "addCustomer";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id, Model model) {

        Optional<Customer> customerOptional = customerService.getCustomerById(id);

        if (customerOptional.isPresent()) {
            model.addAttribute("customer", customerOptional.get());
            return "editCustomer";
        }else {
            return "redirect:/api/customers";
        }
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Integer id,@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.updateCustomer(id, customer);
            return "redirect:/api/customers/";
        }catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "editCustomer";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/api/customers";
    }


}
