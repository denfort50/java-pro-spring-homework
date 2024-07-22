package ru.dkalchenko.repository;

import org.springframework.stereotype.Repository;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.model.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Product> save(Product product) {
        Optional<Product> result;
        String sql = "INSERT INTO main.products(account_number, balance, product_type) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, product.getAccountNumber());
            ps.setDouble(2, product.getBalance());
            ps.setString(3, product.getProductType().toString());
            ps.execute();
            ResultSet it = ps.getGeneratedKeys();
            if (it.next()) {
                product.setId(it.getLong(1));
            }
            result = Optional.of(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void saveForUser(Long userId, Long productId) {
        String sql = "INSERT INTO main.users_products (user_id, product_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, userId);
            ps.setLong(2, productId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Product> findProductById(Long id) {
        Optional<Product> result = Optional.empty();
        String sql = "SELECT * FROM main.products WHERE id = ?";
        try {
            PreparedStatement ps =  connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet it = ps.executeQuery();
            if (it.next()) {
                result = Optional.of(createProduct(it));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Product> findProductsByUserId(Long id) {
        List<Product> productList = new ArrayList<>();
        String sql = """
                SELECT * FROM main.products p
                JOIN main.users_products up on up.product_id = p.id
                WHERE up.user_id = ?""";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    productList.add(createProduct(it));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM main.products";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    productList.add(createProduct(it));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    private Product createProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getLong("id"), rs.getLong("account_number"),
                rs.getDouble("balance"), ProductType.valueOf(rs.getString("product_type")));
    }

}
