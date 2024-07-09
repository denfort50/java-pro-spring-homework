package ru.dkalchenko.repository;

import org.springframework.stereotype.Repository;
import ru.dkalchenko.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private final DataSource pool;

    public UserDao(DataSource dataSource) {
        this.pool = dataSource;
    }

    public Optional<User> save(User user) {
        Optional<User> result;
        String sql = "INSERT INTO main.users(username) VALUES (?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.username());
            ps.execute();
            ResultSet it = ps.getGeneratedKeys();
            if (it.next()) {
                user.setId(it.getLong(1));
            }
            result = Optional.of(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM main.users WHERE id = ?";
        Optional<User> result = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                result = Optional.of(createUser(it));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public boolean deleteById(Long id) {
        boolean result;
        String sql = "DELETE FROM main.users WHERE id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(sql)) {
            ps.setLong(1, id);
            int updated = ps.executeUpdate();
            result = 1 == updated;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM main.users";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    userList.add(createUser(it));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    private User createUser(ResultSet rs) throws SQLException {
        return new User(rs.getLong("id"), rs.getString("username"));
    }
}
