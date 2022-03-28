package com.carvajal.whishlist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name="wishes")
@Data
@Entity
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="state")
    private char state;
    @Column(name="registration_date")
    private Date registrationDate;
    @Column(name="removal_date")
    private Date removalDate;

    @ManyToOne
    @JoinColumn(name = "FK_customer", referencedColumnName = "id", nullable = false, updatable = false)
    @JsonBackReference("customer-wish")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "FK_product", referencedColumnName = "id", nullable = false, updatable = false)
    //@JsonBackReference("product-wish")
    private Product product;


}