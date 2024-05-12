package uz.pdp.task2.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.repo.bace.Repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.pdp.task2.listener.MyListener.emf;

public class UserRepo extends Repo<User, UUID> {
    public static List<User> findAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("SELECT c FROM User c", User.class).getResultList();
        em.close();
        return users;
    }

    public User findByUserName(String email) {

        EntityManager entityManager = emf.createEntityManager();
        Query nativeQuery = entityManager.createNativeQuery("select * from users where username = ?", User.class);
        nativeQuery.setParameter(1, email);
        return (User) nativeQuery.getSingleResult();
    }
}
