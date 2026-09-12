package nz.ac.aut.comp713.coffee.service;

import nz.ac.aut.comp713.coffee.dto.OrderRequest;
import nz.ac.aut.comp713.coffee.dto.OrderResponse;
import nz.ac.aut.comp713.coffee.model.MenuItem;
import nz.ac.aut.comp713.coffee.model.Order;
import nz.ac.aut.comp713.coffee.model.OrderItem;
import nz.ac.aut.comp713.coffee.model.OrderStatus;
import nz.ac.aut.comp713.coffee.repository.MenuItemRepository;
import nz.ac.aut.comp713.coffee.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(OrderRepository orderRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest request) {
        MenuItem menuItem = menuItemRepository.findById(request.menuItemId())
                .orElseThrow(() -> new MenuItemNotFoundException(request.menuItemId()));

        if (!menuItem.isAvailable()) {
            throw new MenuItemUnavailableException(menuItem.getName());
        }

        Order order = new Order(request.customerName());
        OrderItem item = new OrderItem(menuItem, request.quantity());
        order.addItem(item);

        BigDecimal total = menuItem.getPrice().multiply(BigDecimal.valueOf(request.quantity()));
        order.setTotalPrice(total);

        Order saved = orderRepository.save(order);
        return toResponse(saved);
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return toResponse(order);
    }

    @Transactional
    public OrderResponse updateStatus(Long id, OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        order.setStatus(newStatus);
        return toResponse(order);
    }

    private OrderResponse toResponse(Order order) {
        OrderItem firstItem = order.getItems().get(0);
        return new OrderResponse(
                order.getId(),
                order.getCustomerName(),
                firstItem.getMenuItem().getName(),
                firstItem.getQuantity(),
                order.getTotalPrice(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}