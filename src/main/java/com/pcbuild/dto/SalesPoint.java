package com.pcbuild.dto;

public class SalesPoint {

    private String label;
    private double value;

    public SalesPoint(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }
}
