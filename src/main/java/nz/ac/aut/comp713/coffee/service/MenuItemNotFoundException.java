package nz.ac.aut.comp713.coffee.service;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(Long id) {
        super("Menu item " + id + " does not exist");
    }
}