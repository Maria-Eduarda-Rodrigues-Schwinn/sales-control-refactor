package com.salescontrol.data.product;

import com.salescontrol.data.JPAUtil;
import com.salescontrol.domain.Product;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;

public class ProductDao {

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            action.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void save(Product product) {
        executeInsideTransaction(em -> em.persist(product));
    }

    public List<Product> getAllProducts() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("FROM Product", Product.class).getResultList();
        }
    }

    public void update(Product product) {
        executeInsideTransaction(em -> em.merge(product));
    }

    public boolean delete(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Product> searchProductsByName(String name) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("FROM Product WHERE name LIKE :name", Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        }
    }

    public Product getProductById(int id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.find(Product.class, id);
        }
    }

    public void updateProductQuantity(int productId, int quantity) {
        executeInsideTransaction(em -> {
            Product product = em.find(Product.class, productId);
            if (product != null) {
                product.setQuantity(quantity);
                em.merge(product);
            }
        });
    }
}
