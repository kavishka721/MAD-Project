package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class MotherBoard implements Serializable {

    String name;
    String cpu;
    String ram;
    String vga;
    Integer price;
    Integer warranty;

    public MotherBoard() {
    }

    public MotherBoard(String name, String cpu, String ram, String vga, Integer price, Integer warranty) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.vga = vga;
        this.price = price;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
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
