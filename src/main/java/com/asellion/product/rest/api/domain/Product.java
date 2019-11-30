package com.asellion.product.rest.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CURRENTPRICE")
    private double currentPrice;

    @Column(name = "LASTUPDATE")
    private LocalDateTime lastUpdate;


}
