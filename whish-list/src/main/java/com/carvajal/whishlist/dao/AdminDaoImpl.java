package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Admin;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Admin> getAll() {
        String query ="FROM Admin ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Admin getById(int id) {
        return entityManager.find(Admin.class, id);
    }

    @Override
    public void insert(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public void edit(Admin admin, int id) {
        admin.setId(id);
        entityManager.merge(admin);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Admin.class, id));
    }
}
