package uz.pdp.task2.repo.bace;

import jakarta.persistence.EntityManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static uz.pdp.task2.listener.MyListener.emf;

public class Repo<T, ID> {
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public Repo() {
        this.entityClass =(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> findAll(){
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("SELECT e FROM "+entityClass.getSimpleName()+" e", entityClass).getResultList();
    }

    public T findById(ID id){
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(entityClass, id);
    }

    public void save(T t){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    public void update(T t){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }

    public void delete(T t){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void deleteById(ID id){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            T t = entityManager.find(entityClass, id);
            entityManager.remove(entityManager.merge(t));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }

    }
}
