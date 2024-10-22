package com.pizzastore.customerservice.security;

    import com.pizzastore.customerservice.model.Customer;
    import com.pizzastore.customerservice.repository.CustomerRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.User;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;

    @Service
    public class JwtUserDetailsService implements UserDetailsService {

      @Autowired
      private CustomerRepository customerRepository;

      @Override
      public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email)
          .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
      }
    }