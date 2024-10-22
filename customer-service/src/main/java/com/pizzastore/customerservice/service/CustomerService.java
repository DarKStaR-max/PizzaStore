package com.pizzastore.customerservice.service;

    import com.pizzastore.customerservice.model.Customer;
    import com.pizzastore.customerservice.repository.CustomerRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class CustomerService {
      @Autowired
      private CustomerRepository customerRepository;

      public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
      }

      public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
      }

      public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
      }

      public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
      }

      public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
      }

      public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
      }
    }