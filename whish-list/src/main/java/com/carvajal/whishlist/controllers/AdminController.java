package com.carvajal.whishlist.controllers;

import com.carvajal.whishlist.dao.AdminDao;
import com.carvajal.whishlist.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AdminController {
    @Autowired
    AdminDao adminDao;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="api/admin")
    public List<Admin> getAll(){
        return adminDao.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="api/admin/{id}")
    public Admin getById(@PathVariable int id){
        return adminDao.getById(id);
    }

    @RequestMapping(value ="api/admin/insert")
    @PostMapping
    public void insert(@RequestBody Admin availability){
        adminDao.insert(availability);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="api/edit-admin/{id}")
    @PutMapping
    public void edit(@RequestBody Admin availability, @PathVariable int id){
        adminDao.edit(availability, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value ="api/delete-admin/{id}")
    @DeleteMapping
    public void delete(@PathVariable int id){
        adminDao.delete(id);
    }
}