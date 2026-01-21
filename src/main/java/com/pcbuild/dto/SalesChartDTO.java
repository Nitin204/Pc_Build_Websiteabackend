package com.pcbuild.dto;

public class SalesChartDTO {

    private String name;   // date OR month label
    private double online;
    private double offline;

    public SalesChartDTO(String name, double online, double offline) {
        this.name = name;
        this.online = online;
        this.offline = offline;
    }

    public String getName() {
        return name;
    }

    public double getOnline() {
        return online;
    }

    public double getOffline() {
        return offline;
    }
}
