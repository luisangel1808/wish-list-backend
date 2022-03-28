package com.carvajal.whishlist.controllers;

import com.carvajal.whishlist.dao.ProductDao;
import com.carvajal.whishlist.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductDao productDao;

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value ="api/product")
    public List<Product> getAll(){
        return productDao.getAll();
    }

    @RequestMapping(value ="api/product/{id}")
    public Product getById(@PathVariable int id){
        return productDao.getById(id);
    }

    @RequestMapping(value ="api/edit-product/{id}")
    @PutMapping
    public void edit(@PathVariable int id, @RequestBody Product product){
            productDao.edit(product, id);
    }

    @RequestMapping(value ="api/product/insert")
    @PostMapping
    public Product insert(@RequestBody Product product){
        return productDao.insert(product);
    }

    @RequestMapping(value ="api/delete-product/{id}")
    @DeleteMapping
    public void delete(@PathVariable int id){
        productDao.delete(id);
    }
}
