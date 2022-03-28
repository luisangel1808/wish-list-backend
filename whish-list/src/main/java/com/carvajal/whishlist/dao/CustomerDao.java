package com.carvajal.whishlist.dao;

import com.carvajal.whishlist.model.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> getAll();
    Customer getById(int id);
    void insert(Customer customer);
    void edit(Customer customer, int id);
    void delete(int id);
}
