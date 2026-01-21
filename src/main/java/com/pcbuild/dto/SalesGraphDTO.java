package com.pcbuild.dto;

public class SalesGraphDTO {

    private String name;     // date or month
    private double online;
    private double offline;

    public SalesGraphDTO(String name, double online, double offline) {
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
