package com.example.client.orders;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrdersTemp {
    private int ID;
    private String name;
    private String email;
    private String productName;
    private String price;
    private String count;
    public OrdersTemp() {}
    public OrdersTemp(int ID, String name, String email, String productName, String price, String count) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }
}
