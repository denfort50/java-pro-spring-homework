package ru.dkalchenko.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.dkalchenko.model.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Sql("/sql/users.sql")
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenSaveThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void whenFindByIdThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User savedUser = userRepository.save(user);
        Optional<User> userInDb = userRepository.findById(savedUser.getId());
        assertThat(userInDb).isNotEmpty();
    }

    @Test
    public void whenDeleteByIdThenSuccess() {
        User user = User.builder()
                .username("Alex")
                .build();
        User savedUser = userRepository.save(user);
        userRepository.deleteById(savedUser.getId());
        Optional<User> userInDb = userRepository.findById(savedUser.getId());
        assertThat(userInDb).isEmpty();
    }

    @Test
    public void whenFindAllThenSuccess() {
        User user1 = User.builder()
                .username("Alex")
                .build();
        User user2 = User.builder()
                .username("Sam")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> users = userRepository.findAll();
        user1.setId(1L);
        user2.setId(2L);
        assertThat(users).hasSize(2);
    }

}
