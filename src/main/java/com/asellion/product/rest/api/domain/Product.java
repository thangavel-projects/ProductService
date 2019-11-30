package com.asellion.product.rest.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 7372213563040610329L;

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
