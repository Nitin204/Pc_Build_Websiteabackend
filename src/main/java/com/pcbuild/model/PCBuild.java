package com.pcbuild.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pc_builds")
public class PCBuild {

    @Id
    private String id;

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Map<String, String> getSpecs() {
		return specs;
	}
	public void setSpecs(Map<String, String> specs) {
		this.specs = specs;
	}
	public List<String> getFeatures() {
		return features;
	}
	public void setFeatures(List<String> features) {
		this.features = features;
	}
	public List<String> getWarranty() {
		return warranty;
	}
	public void setWarranty(List<String> warranty) {
		this.warranty = warranty;
	}
	private String name;
    private Double price;
    private Double discountPrice;
    private Integer quantity;

    private String image; // ✅ BASE64 STRING

    private Map<String, String> specs;
    private List<String> features;
    private List<String> warranty;

    // getters & setters
}
