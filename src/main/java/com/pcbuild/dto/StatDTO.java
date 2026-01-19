package com.pcbuild.dto;

public class StatDTO {
    private String label;
    private String value;

    public StatDTO(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() { return label; }
    public String getValue() { return value; }
}
