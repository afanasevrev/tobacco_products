package com.example.Server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private String price;
    public Products() {}
    public Products(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }
}
