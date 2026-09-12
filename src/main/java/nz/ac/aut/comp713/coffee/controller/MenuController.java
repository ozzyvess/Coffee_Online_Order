package nz.ac.aut.comp713.coffee.controller;

import jakarta.validation.Valid;
import nz.ac.aut.comp713.coffee.dto.MenuItemRequest;
import nz.ac.aut.comp713.coffee.model.MenuItem;
import nz.ac.aut.comp713.coffee.repository.MenuItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

    private final MenuItemRepository menuItemRepository;

    public MenuController(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @GetMapping
    public List<MenuItem> getMenu() {
        return menuItemRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem addMenuItem(@Valid @RequestBody MenuItemRequest request) {
        MenuItem item = new MenuItem(request.name(), request.size(), request.price(), true);
        return menuItemRepository.save(item);
    }
}