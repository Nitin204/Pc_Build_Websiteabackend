package com.pcbuild.dto;

public class CategoryDTO {
    private String name;
    private int value;

    public CategoryDTO(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public int getValue() { return value; }
}
