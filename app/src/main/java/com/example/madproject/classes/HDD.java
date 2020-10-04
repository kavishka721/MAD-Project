package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class HDD implements Serializable {

    String name;
    String cache;
    String rpm;
    String size;
    Integer price;
    Integer warranty;

    public HDD() {
    }

    public HDD(String name, String cache, String rpm, String size, Integer price, Integer warranty) {
        this.name = name;
        this.cache = cache;
        this.rpm = rpm;
        this.size = size;
        this.price = price;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }
}
