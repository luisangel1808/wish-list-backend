package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Wish;

import java.util.List;

public interface WishListDao {

    void insert(Wish wish);
    List<Wish> getAllByClient();
    List<Wish> getActiveByClient();
    void delete(int id);
}
