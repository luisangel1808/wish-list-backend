package com.carvajal.whishlist.controllers;

import com.carvajal.whishlist.dao.CustomerDao;
import com.carvajal.whishlist.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    CustomerDao customerDao;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="api/customers")
    public List<Customer> getAll(){
        return customerDao.getAll();
    }

    @RequestMapping(value ="api/customer/{id}")
    public Customer getById(@PathVariable int id){
        return customerDao.getById(id);
    }

    @RequestMapping(value ="api/customer/insert")
    @PostMapping
    public void insert(@RequestBody Customer customer){
        customerDao.insert(customer);
    }

    @RequestMapping(value ="api/edit-customer/{id}")
    @PutMapping
    public void edit(@RequestBody Customer customer, @PathVariable int id){
        customerDao.edit(customer, id);
    }

    @RequestMapping(value ="api/delete-customer/{id}")
    @DeleteMapping
    public void delete(@PathVariable int id){
        customerDao.delete(id);
    }
}
