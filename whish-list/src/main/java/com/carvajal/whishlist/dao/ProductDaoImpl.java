package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Image;
import com.carvajal.whishlist.model.Product;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getAll() {
        String query ="FROM Product WHERE state = 'V' ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product insert(Product product) {
        entityManager.persist(product);
        return product;
    }

    @Override
    public void edit(Product product, int id) {
        product.setId(id);
        entityManager.merge(product);
    }

    @Override
    public void delete(int id) {
        Product product = entityManager.find(Product.class, id);
        product.setState('D');
        entityManager.merge(product);
    }
}
