package com.example.madproject.classes;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@SuppressWarnings("Serial")
@IgnoreExtraProperties
public class CPUBuild implements Serializable {

    private Casing casing;
    private CPU cpu;
    private RAM ram;
    private GraphicCard graphicCard;
    private PowerSupply powerSupply;
    private HDD hdd;
    private SSD ssd;
    private Cooling cooling;
    private MotherBoard motherBoard;
    private Integer total;

    public CPUBuild() {
    }

    public CPUBuild(Casing casing, CPU cpu, RAM ram, GraphicCard graphicCard, PowerSupply powerSupply, HDD hdd, SSD ssd, Cooling cooling, MotherBoard motherBoard, Integer total) {
        this.casing = casing;
        this.cpu = cpu;
        this.ram = ram;
        this.graphicCard = graphicCard;
        this.powerSupply = powerSupply;
        this.hdd = hdd;
        this.ssd = ssd;
        this.cooling = cooling;
        this.motherBoard = motherBoard;
        this.total = total;
    }

    public RAM getRam() {
        return ram;
    }

    public void setRam(RAM ram) {
        this.ram = ram;
    }

    public GraphicCard getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCard graphicCard) {
        this.graphicCard = graphicCard;
    }

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public HDD getHdd() {
        return hdd;
    }

    public void setHdd(HDD hdd) {
        this.hdd = hdd;
    }

    public SSD getSsd() {
        return ssd;
    }

    public void setSsd(SSD ssd) {
        this.ssd = ssd;
    }

    public Cooling getCooling() {
        return cooling;
    }

    public void setCooling(Cooling cooling) {
        this.cooling = cooling;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public void setMotherBoard(MotherBoard motherBoard) {
        this.motherBoard = motherBoard;
    }

    public Casing getCasing() {
        return casing;
    }

    public void setCasing(Casing casing) {
        this.casing = casing;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
