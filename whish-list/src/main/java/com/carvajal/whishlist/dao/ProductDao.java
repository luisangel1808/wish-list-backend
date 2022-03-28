package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product getById(int id);
    Product insert(Product product);
    void edit(Product product, int id);
    void delete(int id);
}
