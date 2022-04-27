package ua.com.sergeiokon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.sergeiokon.repository.UserRepository;
import ua.com.sergeiokon.repository.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with   " + email + " not found"));
    }

    public User save(User user) {
        if (user != null) {
            if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            } else {
                throw new IllegalArgumentException("The user with this " + user.getEmail() + " is already registered");
            }
        }
        return null;
    }

    public User update(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with id " + user.getId() + " not found");
        }
    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("User with id " + id + " not found");
    }
}
