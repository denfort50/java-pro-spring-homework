package ru.dkalchenko.service;

import org.springframework.stereotype.Service;
import ru.dkalchenko.model.User;
import ru.dkalchenko.repository.UserDao;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User save(User user) {
        Optional<User> userOptional = userDao.save(user);
        return userOptional.orElseThrow(() -> new RuntimeException("Не удалось сохранить пользователя"));
    }

    public User findById(Long id) {
        Optional<User> userOptional = userDao.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException("Не удалось найти пользователя по id: " + id));
    }

    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
