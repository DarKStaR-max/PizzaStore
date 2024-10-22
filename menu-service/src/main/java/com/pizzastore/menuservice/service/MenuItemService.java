package com.pizzastore.menuservice.service;

    import com.pizzastore.menuservice.model.MenuItem;
    import com.pizzastore.menuservice.repository.MenuItemRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class MenuItemService {
      @Autowired
      private MenuItemRepository menuItemRepository;

      public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
      }

      public Optional<MenuItem> getMenuItemById(Long id) {
        return menuItemRepository.findById(id);
      }

      public List<MenuItem> getMenuItemsByCategory(MenuItem.Category category) {
        return menuItemRepository.findByCategory(category);
      }

      public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
      }

      public MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
      }

      public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
      }
    }