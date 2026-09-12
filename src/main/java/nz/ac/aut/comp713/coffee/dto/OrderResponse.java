package nz.ac.aut.comp713.coffee.dto;

import nz.ac.aut.comp713.coffee.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
        Long id,
        String customerName,
        String itemName,
        int quantity,
        BigDecimal totalPrice,
        OrderStatus status,
        LocalDateTime createdAt
) {
}