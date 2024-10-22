package com.pizzastore.customerservice.controller;

import com.pizzastore.customerservice.model.Customer;
import com.pizzastore.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer loginCustomer) {
        Customer customer = customerService.getCustomerByEmail(loginCustomer.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginCustomer.getEmail()));

        if (passwordEncoder.matches(loginCustomer.getPassword(), customer.getPassword())) {
            return ResponseEntity.ok(customer);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer registeredCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(registeredCustomer);
    }
}