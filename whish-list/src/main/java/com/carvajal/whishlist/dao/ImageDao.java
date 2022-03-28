package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Image;

import java.util.List;

public interface ImageDao {
    List<Image> getAll();
    Image getById(int id);
    void insert(Image availability);
    void edit(Image availability, int id);
    void delete(int id);
}
