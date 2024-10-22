package com.pizzastore.menuservice.controller;

    import com.pizzastore.menuservice.model.MenuItem;
    import com.pizzastore.menuservice.service.MenuItemService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/menu-items")
    public class MenuItemController {
      @Autowired
      private MenuItemService menuItemService;

      @GetMapping
      public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
      }

      @GetMapping("/{id}")
      public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
      }

      @GetMapping("/category/{category}")
      public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable MenuItem.Category category) {
        return ResponseEntity.ok(menuItemService.getMenuItemsByCategory(category));
      }

      @PostMapping
      public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(menuItem));
      }

      @PutMapping("/{id}")
      public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuItemService.getMenuItemById(id)
          .map(existingMenuItem -> {
            menuItem.setId(id);
            return ResponseEntity.ok(menuItemService.updateMenuItem(menuItem));
          })
          .orElse(ResponseEntity.notFound().build());
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        return menuItemService.getMenuItemById(id)
          .map(menuItem -> {
            menuItemService.deleteMenuItem(id);
            return ResponseEntity.ok().<Void>build();
          })
          .orElse(ResponseEntity.notFound().build());
      }
    }