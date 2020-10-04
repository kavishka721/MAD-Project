package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class SSD implements Serializable {

    String name;
    String cache;
    String msr;
    String msw;
    String size;
    Integer price;
    Integer warranty;

    public SSD() {
    }

    public SSD(String name, String cache, String msr, String msw, String size, Integer price, Integer warranty) {
        this.name = name;
        this.cache = cache;
        this.msr = msr;
        this.msw = msw;
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

    public String getMsr() {
        return msr;
    }

    public void setMsr(String msr) {
        this.msr = msr;
    }

    public String getMsw() {
        return msw;
    }

    public void setMsw(String msw) {
        this.msw = msw;
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
