package uz.pdp.task2.repo;

import jakarta.persistence.EntityManager;
import uz.pdp.task2.entity.OrderProduct;
import uz.pdp.task2.repo.bace.Repo;

import java.util.List;
import java.util.UUID;

import static uz.pdp.task2.listener.MyListener.emf;

public class OrderProductRepo extends Repo<OrderProduct, Integer> {
    public void deleteByOrderId(Integer orderId) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM OrderProduct op WHERE op.order.id = :orderId")
                .setParameter("orderId", orderId)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveAll(List<OrderProduct> orderProducts) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        for (OrderProduct orderProduct : orderProducts) {
            entityManager.persist(orderProduct);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
