package ru.dkalchenko;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dkalchenko.config.SpringConfig;
import ru.dkalchenko.model.User;
import ru.dkalchenko.service.UserService;

import java.util.List;

public class SpringApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean(UserService.class);

        User newUser = new User(null, "Denis");
        User savedUser = userService.save(newUser);
        System.out.println("Добавлен пользователь: " + savedUser);

        Long id = 1L;
        User userFromDb = userService.findById(id);
        System.out.println("Найден пользователь: " + userFromDb);

        boolean ifDeleted = userService.deleteById(id);
        System.out.println("Пользователь удалён? Ответ: " + ifDeleted);

        User admin = new User(null, "Admin");
        User user = new User(null, "User");
        userService.save(admin);
        userService.save(user);
        List<User> userList = userService.findAll();
        System.out.println("Пользователей в БД: " + userList.size());
    }
}
