package nz.ac.aut.comp713.coffee.service;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Order " + id + " does not exist");
    }
}