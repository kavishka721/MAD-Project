package com.example.madproject.classes;

import com.example.madproject.classes.CPUs;
import com.example.madproject.classes.Casing;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@SuppressWarnings("Serial")
@IgnoreExtraProperties
public class CPUBuild implements Serializable {

    private Casing casing;
    private CPUs cpu;

    public CPUBuild() {
    }

    public CPUBuild(Casing casing, CPUs cpu) {
        this.casing = casing;
        this.cpu = cpu;
    }

    public Casing getCasing() {
        return casing;
    }

    public void setCasing(Casing casing) {
        this.casing = casing;
    }

    public CPUs getCpu() {
        return cpu;
    }

    public void setCpu(CPUs cpu) {
        this.cpu = cpu;
    }
}
