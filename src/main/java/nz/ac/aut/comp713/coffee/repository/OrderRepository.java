package nz.ac.aut.comp713.coffee.repository;

import nz.ac.aut.comp713.coffee.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}