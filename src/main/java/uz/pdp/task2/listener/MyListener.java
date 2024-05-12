package uz.pdp.task2.listener;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.awt.event.ActionListener;
@WebListener
public class MyListener implements ServletContextListener {
    public static EntityManagerFactory emf;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("task2");
        ServletContextListener.super.contextInitialized(sce);
    }
}
