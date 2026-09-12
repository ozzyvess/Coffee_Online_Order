package nz.ac.aut.comp713.coffee.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record MenuItemRequest(
        @NotBlank String name,
        @NotBlank String size,
        @DecimalMin("0.01") BigDecimal price
) {
}