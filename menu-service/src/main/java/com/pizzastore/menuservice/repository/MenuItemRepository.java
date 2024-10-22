package com.pizzastore.menuservice.repository;

    import com.pizzastore.menuservice.model.MenuItem;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
      List<MenuItem> findByCategory(MenuItem.Category category);
    }