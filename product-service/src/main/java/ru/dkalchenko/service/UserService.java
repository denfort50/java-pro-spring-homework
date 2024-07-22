package ru.dkalchenko.service;

import org.springframework.stereotype.Service;
import ru.dkalchenko.model.User;
import ru.dkalchenko.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        Optional<User> userOptional = userRepository.save(user);
        return userOptional.orElseThrow(() -> new RuntimeException("Не удалось сохранить пользователя"));
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException("Не удалось найти пользователя по id: " + id));
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}