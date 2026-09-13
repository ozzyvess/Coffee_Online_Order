package nz.ac.aut.comp713.coffee.controller;

import jakarta.servlet.http.HttpServletRequest;
import nz.ac.aut.comp713.coffee.dto.ApiError;
import nz.ac.aut.comp713.coffee.service.MenuItemNotFoundException;
import nz.ac.aut.comp713.coffee.service.MenuItemUnavailableException;
import nz.ac.aut.comp713.coffee.service.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return error(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", message, request);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ApiError> handleOrderNotFound(
            OrderNotFoundException exception,
            HttpServletRequest request) {

        return error(HttpStatus.NOT_FOUND, "ORDER_NOT_FOUND", exception.getMessage(), request);
    }

    @ExceptionHandler(MenuItemNotFoundException.class)
    public ResponseEntity<ApiError> handleMenuItemNotFound(
            MenuItemNotFoundException exception,
            HttpServletRequest request) {

        return error(HttpStatus.NOT_FOUND, "MENU_ITEM_NOT_FOUND", exception.getMessage(), request);
    }

    @ExceptionHandler(MenuItemUnavailableException.class)
    public ResponseEntity<ApiError> handleMenuItemUnavailable(
            MenuItemUnavailableException exception,
            HttpServletRequest request) {

        return error(HttpStatus.CONFLICT, "MENU_ITEM_UNAVAILABLE", exception.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception exception,
            HttpServletRequest request) {

        return error(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "The request could not be completed", request);
    }

    private ResponseEntity<ApiError> error(
            HttpStatus status,
            String code,
            String message,
            HttpServletRequest request) {

        return ResponseEntity.status(status).body(new ApiError(
                code,
                message,
                request.getRequestURI()
        ));
    }
}