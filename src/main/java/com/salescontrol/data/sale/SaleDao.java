package com.salescontrol.data.sale;

import com.salescontrol.data.JPAUtil;
import com.salescontrol.domain.Sale;
import com.salescontrol.domain.SaleProduct;
import com.salescontrol.enuns.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import java.util.Date;
import java.util.List;

public class SaleDao {

    public void save(Sale sale) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void save(Sale sale, List<SaleProduct> saleProducts) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            sale = em.merge(sale);
            for (SaleProduct saleProduct : saleProducts) {
                saleProduct.setSale(sale);
                saleProduct.setProduct(em.merge(saleProduct.getProduct()));
                em.merge(saleProduct);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public List<Sale> getAllSales() {
        List<Sale> sales;
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Sale> query = em.createQuery("FROM Sale", Sale.class);
            sales = query.getResultList();
            for (Sale sale : sales) {
                Hibernate.initialize(sale.getProductsSold());
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return sales;
    }

    public List<Sale> filterSalesByDate(Date startDate, Date endDate) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("FROM Sale WHERE saleDate BETWEEN :startDate AND :endDate", Sale.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }

    public List<Sale> filterSalesByCategory(Category category) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT s FROM Sale s JOIN s.productsSold p WHERE p.product.category = :category", Sale.class)
                    .setParameter("category", category)
                    .getResultList();
        }
    }

    public List<Sale> filterSalesByProductName(String productName) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT s FROM Sale s JOIN s.productsSold p WHERE p.product.name LIKE :productName", Sale.class)
                    .setParameter("productName", "%" + productName + "%")
                    .getResultList();
        }
    }
}
