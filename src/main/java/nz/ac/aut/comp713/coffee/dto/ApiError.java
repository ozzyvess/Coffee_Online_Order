package nz.ac.aut.comp713.coffee.dto;

public record ApiError(
        String code,
        String message,
        String path
) {
}