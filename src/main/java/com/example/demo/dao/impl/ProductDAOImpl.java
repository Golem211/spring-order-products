package com.example.demo.dao.impl;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        TypedQuery<Product> query = entityManager.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product findByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("from Product p where p.name = :name", Product.class);
        query.setParameter( "name" , name);

        return query.getSingleResult();
    }


    @Override
    public Product save(Product productToSave) {
        return entityManager.merge(productToSave);
    }

    @Override
    public void deleteById(int id) {
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);

    }
}
