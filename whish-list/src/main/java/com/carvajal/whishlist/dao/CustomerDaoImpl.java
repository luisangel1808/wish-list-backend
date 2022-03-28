package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> getAll() {
        String query ="FROM Customer ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Customer getById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void insert(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void edit(Customer customer, int id) {
        customer.setId(id);
        entityManager.merge(customer);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Customer.class, id));
    }
}