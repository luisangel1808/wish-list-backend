package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Customer;
import com.carvajal.whishlist.model.Wish;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class WishListDaoImpl implements WishListDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insert(@RequestBody Wish wish) {
        Customer customer = new Customer();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String queryUser ="SELECT customers.id FROM customers INNER JOIN user_ ON customers.user_id = user_.id WHERE user_.user_name=:cst";
        int customerId = (int) entityManager.createNativeQuery(queryUser).setParameter("cst",securityContext.getAuthentication().getName()).getSingleResult();
        entityManager.close();
        customer.setId(customerId);
        wish.setCustomer(customer);
        entityManager.persist(wish);
    }

    @Override
    public List<Wish> getAllByClient() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String queryUser ="SELECT customers.id FROM customers INNER JOIN user_ ON customers.user_id = user_.id WHERE user_.user_name=:cst";
        int customerId = (int) entityManager.createNativeQuery(queryUser).setParameter("cst",securityContext.getAuthentication().getName()).getSingleResult();
        entityManager.close();

        String query ="FROM Wish WHERE fk_customer = :customerId";
        return entityManager.createQuery(query).setParameter("customerId", customerId).getResultList();
    }

    @Override
    public List<Wish> getActiveByClient() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String queryUser ="SELECT customers.id FROM customers INNER JOIN user_ ON customers.user_id = user_.id WHERE user_.user_name=:cst";
        try{
            int customerId = (int) entityManager.createNativeQuery(queryUser).setParameter("cst",securityContext.getAuthentication().getName()).getSingleResult();
            entityManager.close();
            String query ="FROM Wish WHERE fk_customer = :customerId AND state = 'V' ";
            return entityManager.createQuery(query).setParameter("customerId", customerId).getResultList();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(int id)  {
        Wish wish = entityManager.find(Wish.class, id);
        wish.setState('D');
        wish.setRemovalDate(new Date());
        entityManager.merge(wish);
    }
}
