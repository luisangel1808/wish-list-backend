package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ImageDaoImpl implements ImageDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Image> getAll() {
        String query ="FROM Image ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Image getById(int id) {
        return entityManager.find(Image.class, id);
    }

    @Override
    public void insert(Image image) {
        entityManager.persist(image);
    }

    @Override
    public void edit(Image image, int id) {
        image.setId(id);
        entityManager.merge(image);
    }

    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(Image.class, id));
    }
}
