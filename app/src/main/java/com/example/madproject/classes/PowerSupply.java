package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("Serial")
public class PowerSupply implements Serializable {

    String name;
    String intelspec;
    String acin;
    String maximum_load;
    Integer price;
    String total_out;
    Integer warranty;

    public PowerSupply() {
    }

    public PowerSupply(String name, String intelspec, String acin, String maximum_load, Integer price, String total_out, Integer warranty) {
        this.name = name;
        this.intelspec = intelspec;
        this.acin = acin;
        this.maximum_load = maximum_load;
        this.price = price;
        this.total_out = total_out;
        this.warranty = warranty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntelspec() {
        return intelspec;
    }

    public void setIntelspec(String intelspec) {
        this.intelspec = intelspec;
    }

    public String getAcin() {
        return acin;
    }

    public void setAcin(String acin) {
        this.acin = acin;
    }

    public String getMaximum_load() {
        return maximum_load;
    }

    public void setMaximum_load(String maximum_load) {
        this.maximum_load = maximum_load;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTotal_out() {
        return total_out;
    }

    public void setTotal_out(String total_out) {
        this.total_out = total_out;
    }

    public Integer getWarranty() {
        return warranty;
    }

    public void setWarranty(Integer warranty) {
        this.warranty = warranty;
    }
}
