package com.carvajal.whishlist.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table(name="products")
@Data
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;
    @Column(name="stock")
    private int stock;
    @Column(name="state")
    private char state;

    @OneToMany( mappedBy = "product")
    //@JsonManagedReference("product-wish")
    @JsonIgnore
    private List<Wish> wishes;

    @OneToMany(mappedBy="product")
    @JsonManagedReference("product-images")
    private List<Image> images;

}
