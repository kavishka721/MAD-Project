package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class Casing implements Serializable {

    String Name;
    String Case_Size;
    String Weight;
    Integer Price;
    Integer warranty;


    public Casing() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCase_Size() {
        return Case_Size;
    }

    public void setCase_Size(String case_Size) {
        Case_Size = case_Size;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }
}
