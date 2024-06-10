package com.example.client.orders;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orders {
    public StringProperty ID;
    public StringProperty name;
    public StringProperty email;
    public StringProperty productName;
    public StringProperty price;
    public StringProperty count;
    public Orders(String ID, String name, String email, String productName, String price, String count) {
        this.ID = new SimpleStringProperty(this,"ID", ID);
        this.name = new SimpleStringProperty(this, "name", name);
        this.email = new SimpleStringProperty(this, "email", email);
        this.productName = new SimpleStringProperty(this, "productName", productName);
        this.price = new SimpleStringProperty(this, "price", price);
        this.count = new SimpleStringProperty(this, "count", count);
    }
    public String getID() {
        return ID.get();
    }
    public StringProperty IDProperty() {
        return ID;
    }
    public void setID(String ID) {
        this.ID.set(ID);
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getEmail() {
        return email.get();
    }
    public StringProperty emailProperty() {
        return email;
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public String getProductName() {
        return productName.get();
    }
    public StringProperty productNameProperty() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName.set(productName);
    }
    public String getPrice() {
        return price.get();
    }
    public StringProperty priceProperty() {
        return price;
    }
    public void setPrice(String price) {
        this.price.set(price);
    }
    public String getCount() {
        return count.get();
    }
    public StringProperty countProperty() {
        return count;
    }
    public void setCount(String count) {
        this.count.set(count);
    }
}
