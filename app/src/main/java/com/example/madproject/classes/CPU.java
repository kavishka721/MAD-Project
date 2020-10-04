package com.example.madproject.classes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CPU implements Serializable {
    String CPU_ID;
    String Name;
    Integer CPU_Cores;
    Integer Threads;
    Integer L1_Cache;
    Integer L2_Cache;
    Integer L3_Cache;
    Double Base_Clock;
    Double Max_Boost_Clock;
    Integer Price;
    Integer Warranty;

    public CPU() {
    }

    public String getCPU_ID() {
        return CPU_ID;
    }

    public void setCPU_ID(String CPU_ID) {
        this.CPU_ID = CPU_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getCPU_Cores() {
        return CPU_Cores;
    }

    public void setCPU_Cores(Integer CPU_Cores) {
        this.CPU_Cores = CPU_Cores;
    }

    public Integer getThreads() {
        return Threads;
    }

    public void setThreads(Integer threads) {
        Threads = threads;
    }

    public Integer getL1_Cache() {
        return L1_Cache;
    }

    public void setL1_Cache(Integer l1_Cache) {
        L1_Cache = l1_Cache;
    }

    public Integer getL2_Cache() {
        return L2_Cache;
    }

    public void setL2_Cache(Integer l2_Cache) {
        L2_Cache = l2_Cache;
    }

    public Integer getL3_Cache() {
        return L3_Cache;
    }

    public void setL3_Cache(Integer l3_Cache) {
        L3_Cache = l3_Cache;
    }

    public Double getBase_Clock() {
        return Base_Clock;
    }

    public void setBase_Clock(Double base_Clock) {
        Base_Clock = base_Clock;
    }

    public Double getMax_Boost_Clock() {
        return Max_Boost_Clock;
    }

    public void setMax_Boost_Clock(Double max_Boost_Clock) {
        Max_Boost_Clock = max_Boost_Clock;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getWarranty() {
        return Warranty;
    }

    public void setWarranty(Integer warranty) {
        Warranty = warranty;
    }
}
