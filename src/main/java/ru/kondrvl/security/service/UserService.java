package ru.kondrvl.security.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kondrvl.security.AuthorizedUser;
import ru.kondrvl.security.model.User;
import ru.kondrvl.security.repository.UserRepository;

import java.util.List;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email.toLowerCase()).orElseThrow(() ->
                new UsernameNotFoundException("User " + email + " is not found"));
        return new AuthorizedUser(user);
    }

    public User create(User user){
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email.toLowerCase()).orElseThrow(() ->
                new UsernameNotFoundException("User " + email + " is not found"));
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
