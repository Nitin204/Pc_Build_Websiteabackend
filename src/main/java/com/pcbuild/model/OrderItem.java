package com.pcbuild.model;

public class OrderItem {
    private String productId;
    private ProductType productType;
    private String name;
    private double price;
    private int quantity;
    private String image;

    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public ProductType getProductType() {
        return productType;
    }
    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public double getPrice() { 
        return price; 
    }
    public void setPrice(double price) { 
        this.price = price; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public void setQuantity(int quantity) { 
        this.quantity = quantity; 
    }
    public String getImage() { 
        return image; 
    }
    public void setImage(String image) { 
        this.image = image; 
    }
}
