package com.pizzastore.customerservice.client;

    import org.springframework.cloud.openfeign.FeignClient;
    import org.springframework.web.bind.annotation.GetMapping;

    import java.util.List;

    @FeignClient(name = "menu-service")
    public interface MenuServiceClient {

      @GetMapping("/api/menu-items")
      List<MenuItem> getAllMenuItems();

      // You can add more methods to interact with the Menu Service as needed
    }

    class MenuItem {
      private Long id;
      private String name;
      private String description;
      private Double price;
      private String category;

      // Getters and setters
    }