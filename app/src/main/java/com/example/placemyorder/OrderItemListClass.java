package com.example.placemyorder;

public class OrderItemListClass {

    String productName;
    int quantity;
    String address;

    public OrderItemListClass(String productName , int quantity , String address) {
        this.productName=productName;
        this.quantity=quantity;
        this.address=address;
    }

    public OrderItemListClass() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName=productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }

}
