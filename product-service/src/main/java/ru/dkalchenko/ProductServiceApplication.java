package ru.dkalchenko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dkalchenko.model.Product;
import ru.dkalchenko.model.ProductType;
import ru.dkalchenko.model.User;
import ru.dkalchenko.service.ProductService;
import ru.dkalchenko.service.UserService;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProductServiceApplication.class);
        // TODO Раскомментировать при первом запуске приложения на новой базе данных
        // insertDataIntoDatabase(context);
    }

    private static void insertDataIntoDatabase(ConfigurableApplicationContext context) {
        UserService userService = context.getBean(UserService.class);
        ProductService productService = context.getBean(ProductService.class);

        // Добавляем пользователей в систему
        final int userAmt = 4;
        for (int i = 1; i <= userAmt; i++) {
            User user = new User(null, "User №" + i);
            userService.save(user);
        }

        // Добавляем продукты в систему и присваиваем пользователям
        RandomGenerator generator = RandomGeneratorFactory.getDefault().create();
        final int productAmt = 20;
        int userCounter = 1;
        for (int i = 1; i <= productAmt; i++) {
            long accountNumber = generator.nextLong();
            double balance = generator.nextDouble();
            ProductType productType = (i % 2 != 0) ? ProductType.ACCOUNT : ProductType.CARD;
            Product product = new Product(null, accountNumber, balance, productType);
            Product saved = productService.save(product);
            productService.saveForUser((long) userCounter, saved.getId());
            if (saved.getId() % 5 == 0) {
                userCounter++;
            }
        }
    }

}
