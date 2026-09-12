package nz.ac.aut.comp713.coffee.service;

public class MenuItemUnavailableException extends RuntimeException {
    public MenuItemUnavailableException(String name) {
        super(name + " is currently unavailable");
    }
}