package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Admin;

import java.util.List;

public interface AdminDao {
    List<Admin> getAll();
    Admin getById(int id);
    void insert(Admin availability);
    void edit(Admin availability, int id);
    void delete(int id);
}
