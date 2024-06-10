package com.example.Server.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "orders_temp")
public class OrdersTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private String price;
    @Column(name = "count")
    private String count;
    public OrdersTemp() {}
    public OrdersTemp(String name, String email, String productName, String price, String count) {
        this.name = name;
        this.email = email;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
