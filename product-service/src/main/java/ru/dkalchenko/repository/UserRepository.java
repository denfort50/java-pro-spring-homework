package ru.dkalchenko.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.dkalchenko.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph("graph.User.products")
    @Query(value = "from User u where u.id = ?1")
    Optional<User> findUserWithProducts(Long id);
}
