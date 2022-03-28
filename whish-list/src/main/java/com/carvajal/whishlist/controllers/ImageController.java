package com.carvajal.whishlist.controllers;
import com.carvajal.whishlist.dao.ImageDao;
import com.carvajal.whishlist.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ImageController {
    @Autowired
    ImageDao imageDao;

    @RequestMapping(value ="api/image")
    public List<Image> getAll(){
        return imageDao.getAll();
    }

    @RequestMapping(value ="api/image/{id}")
    public Image getById(@PathVariable int id){
        return imageDao.getById(id);
    }

    @RequestMapping(value ="api/image/insert")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public void insert(@RequestBody Image image){
        imageDao.insert(image);
    }

    @RequestMapping(value ="api/edit-image/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public void edit(@RequestBody Image image, @PathVariable int id){
        imageDao.edit(image, id);
    }

    @RequestMapping(value ="api/delete-image/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public void delete(@PathVariable int id){
        imageDao.delete(id);
    }
}
