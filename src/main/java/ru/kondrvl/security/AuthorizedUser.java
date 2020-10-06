package ru.kondrvl.security;

import ru.kondrvl.security.model.Role;
import ru.kondrvl.security.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.isEnabled(),
                user.getRoles()
                        .stream()
                        .map(Role::getAuthorities)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet()));
        this.user = user;
    }

    public int getId() {
        return user.getId();
    }

    public void update(User newUser) {
        user = newUser;
    }

    public User getUserTo() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
