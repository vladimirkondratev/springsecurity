package ru.kondrvl.security.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kondrvl.security.model.User;
import ru.kondrvl.security.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('users:read')")
    List<User> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:read')")
    User get(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('users:write')")
    public User create(@RequestBody User user){
        return service.create(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('users:write')")
    public void deleteById(@PathVariable int id){
        service.deleteById(id);
    }
}
