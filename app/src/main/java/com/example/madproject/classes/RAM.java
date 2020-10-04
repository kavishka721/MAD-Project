package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class RAM implements Serializable {

    String name;
    String type;
    Integer size;
    Integer speed;
    Integer warranty;
    Integer price;

    public RAM() {
    }

    public RAM(String name, String type, Integer size, Integer speed, Integer warranty, Integer price) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.speed = speed;
        this.warranty = warranty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
