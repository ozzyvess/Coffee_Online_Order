package nz.ac.aut.comp713.coffee.repository;

import nz.ac.aut.comp713.coffee.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}