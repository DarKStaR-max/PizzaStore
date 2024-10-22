package com.pizzastore.customerservice.controller;

    import com.pizzastore.customerservice.model.Customer;
    import com.pizzastore.customerservice.service.CustomerService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/customers")
    public class CustomerController {
      @Autowired
      private CustomerService customerService;

      @GetMapping
      public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
      }

      @GetMapping("/{id}")
      public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
      }

      @PostMapping
      public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customer));
      }

      @PutMapping("/{id}")
      public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.getCustomerById(id)
          .map(existingCustomer -> {
            customer.setId(id);
            return ResponseEntity.ok(customerService.updateCustomer(customer));
          })
          .orElse(ResponseEntity.notFound().build());
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id)
          .map(customer -> {
            customerService.deleteCustomer(id);
            return ResponseEntity.ok().<Void>build();
          })
          .orElse(ResponseEntity.notFound().build());
      }
    }