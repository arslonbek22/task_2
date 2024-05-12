package uz.pdp.task2.listener;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import uz.pdp.task2.entity.Product;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.ProductRepo;
import uz.pdp.task2.repo.UserRepo;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("task2");
        /*User user = User.builder().username("admin").password("admin").role("ADMIN").build();
        User user1 = User.builder().username("user").password("user").role("USER").build();
        new UserRepo().save(user);
        new UserRepo().save(user1);
        List<Product> products = List.of(
                new Product("Iphome 15 pro max", 1260),
                new Product("Iphome 14 pro max", 1000),
                new Product("Iphome 13 pro max", 800),
                new Product("Samsung 24 ultra", 1100),
                new Product("Samsung 23 ultra", 900),
                new Product("Samsung 22 ultra", 500)
        );
        for (Product product : products) {
            new ProductRepo().save(product);
        }*/
        ServletContextListener.super.contextInitialized(sce);
    }
}
