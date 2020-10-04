package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class Cooling implements Serializable {

    String name;
    String fans;
    String fan_noise;
    Integer price;
    Integer warranty;

    public Cooling() {
    }

    public Cooling(String name, String fans, String fan_noise, Integer price, Integer warranty) {
        this.name = name;
        this.fans = fans;
        this.fan_noise = fan_noise;
        this.price = price;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFan_noise() {
        return fan_noise;
    }

    public void setFan_noise(String fan_noise) {
        this.fan_noise = fan_noise;
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
