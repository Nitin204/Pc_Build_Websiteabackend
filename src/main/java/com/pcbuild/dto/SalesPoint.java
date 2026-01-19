package com.pcbuild.dto;

public class SalesPoint {
    private String name;
    private double v;

    public SalesPoint(String name, double v) {
        this.name = name;
        this.v = v;
    }

    public String getName() { return name; }
    public double getV() { return v; }
}
