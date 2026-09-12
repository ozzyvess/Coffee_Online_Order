package nz.ac.aut.comp713.coffee.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotBlank String customerName,
        @NotNull Long menuItemId,
        @Min(1) int quantity
) {
}