package com.pizzastore.adminservice.controller;

import com.pizzastore.adminservice.model.Admin;
import com.pizzastore.adminservice.model.JwtResponse;
import com.pizzastore.adminservice.model.MenuItem;
import com.pizzastore.adminservice.security.JwtTokenUtil;
import com.pizzastore.adminservice.security.JwtUserDetailsService;
import com.pizzastore.adminservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmin() {
        return ResponseEntity.ok(adminService.getAllAdmin());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.ok(createdAdmin);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin loginAdmin) throws Exception {
        authenticate(loginAdmin.getUsername(), loginAdmin.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginAdmin.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/menu-items")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = adminService.createMenuItem(menuItem);
        return ResponseEntity.ok(createdMenuItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menu-items")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = adminService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/menu-items/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        MenuItem menuItem = adminService.getMenuItemById(id);
        return ResponseEntity.ok(menuItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/menu-items/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        MenuItem updatedMenuItem = adminService.updateMenuItem(id, menuItem);
        return ResponseEntity.ok(updatedMenuItem);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/menu-items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        adminService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}