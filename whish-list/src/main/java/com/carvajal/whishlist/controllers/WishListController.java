package com.carvajal.whishlist.controllers;

import com.carvajal.whishlist.dao.WishListDao;
import com.carvajal.whishlist.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class WishListController {
    @Autowired
    WishListDao wishListDao;

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value ="api/wish-list/")
    public List<Wish> getAllByClient(){
        return wishListDao.getAllByClient();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value ="api/wish-list-active/")
    public List<Wish> getActiveByClient(){
        return wishListDao.getActiveByClient();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value ="api/wish-list/insert")
    @PostMapping
    public void insert(@RequestBody Wish wish){
        wishListDao.insert(wish);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @RequestMapping(value ="api/delete-wish/{id}")
    @DeleteMapping
    public void delete(@PathVariable int id){
        wishListDao.delete(id);
    }

}
