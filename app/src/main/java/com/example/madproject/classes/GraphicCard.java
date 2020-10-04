package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class GraphicCard implements Serializable {

    String name;
    Integer price;
    Integer size;
    Integer warranty;

    public GraphicCard() {
    }

    public GraphicCard(String name, Integer price, Integer size, Integer warranty) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }
}
