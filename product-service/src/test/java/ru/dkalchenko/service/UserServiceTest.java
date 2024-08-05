package ru.dkalchenko.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.dkalchenko.exeption.UserNotFoundException;
import ru.dkalchenko.model.User;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@Sql("/sql/users.sql")
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenDeleteByIdThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User savedUser = userService.save(user);
        userService.deleteById(savedUser.getId());
        assertThrows(UserNotFoundException.class, () -> userService.findById(savedUser.getId()),
                "Не удалось найти пользователя по id: 1");
    }
}
