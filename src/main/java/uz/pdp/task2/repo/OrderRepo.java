package uz.pdp.task2.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.task2.entity.Order;
import uz.pdp.task2.repo.bace.Repo;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

import static uz.pdp.task2.listener.MyListener.emf;

public class OrderRepo extends Repo<Order, Integer> {
    public List<Order> findAllStatus(String status) {
        EntityManager entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery("SELECT o FROM Order o WHERE o.status = :status");
        query.setParameter("status", status);
        return query.getResultList();
    }
}
