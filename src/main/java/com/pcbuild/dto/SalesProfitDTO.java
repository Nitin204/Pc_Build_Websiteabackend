package com.pcbuild.dto;

public class SalesProfitDTO {

    private String label;
    private double online;
    private double offline;

    public SalesProfitDTO(String label, double online, double offline) {
        this.label = label;
        this.online = online;
        this.offline = offline;
    }

    public String getLabel() {
        return label;
    }

    public double getOnline() {
        return online;
    }

    public double getOffline() {
        return offline;
    }
}
