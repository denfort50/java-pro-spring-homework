package ru.dkalchenko.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.dkalchenko.exeption.UserNotFoundException;
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
        return userRepository.save(user);
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() ->
                new UserNotFoundException("Не удалось найти пользователя по id: " + id, HttpStatus.NOT_FOUND));
    }

    public User findUserWithProducts(Long id) {
        Optional<User> userOptional = userRepository.findUserWithProducts(id);
        return userOptional.orElseThrow(() ->
                new UserNotFoundException("Не удалось найти пользователя по id: " + id, HttpStatus.NOT_FOUND));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
