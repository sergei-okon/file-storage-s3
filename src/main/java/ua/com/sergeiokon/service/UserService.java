package ua.com.sergeiokon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.sergeiokon.repository.UserRepository;
import ua.com.sergeiokon.repository.entity.File;
import ua.com.sergeiokon.repository.entity.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User with id " + user.getId() + " not found"));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else
            throw new IllegalArgumentException("User with id " + id + " not found");
    }
}
