package com.pizzastore.adminservice.service;

import com.pizzastore.adminservice.model.Admin;
import com.pizzastore.adminservice.model.MenuItem;
import com.pizzastore.adminservice.model.Role;
import com.pizzastore.adminservice.repository.AdminRepository;
import com.pizzastore.adminservice.repository.MenuItemRepository;
import com.pizzastore.adminservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        // Assign a default role (e.g., ADMIN) if necessary
        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        admin.setRoles(Collections.singleton(role));
        return adminRepository.save(admin);
    }


    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public List<Admin> getAllAdmin() {
     return adminRepository.findAll();
    }


    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        if (menuItemRepository.existsById(id)) {
            menuItem.setId(id); // Ensure the ID is set for the update
            return menuItemRepository.save(menuItem);
        }
        return null; // Or throw an exception if preferred
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}