package com.pcbuild.model;



import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "careers")
public class CareerApplication {

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(String appliedOn) {
		this.appliedOn = appliedOn;
	}
	private String name;
    private String email;
    private String phone;
    private String description;
    private String status;
    private String appliedOn;

    // 🔥 RESUME FIELDS (MISSING EARLIER)
    private byte[] resumeData;
    private String resumeName;
    private String resumeType;

    // getters & setters
    public byte[] getResumeData() { return resumeData; }
    public void setResumeData(byte[] resumeData) { this.resumeData = resumeData; }

    public String getResumeName() { return resumeName; }
    public void setResumeName(String resumeName) { this.resumeName = resumeName; }

    public String getResumeType() { return resumeType; }
    public void setResumeType(String resumeType) { this.resumeType = resumeType; }
    
    
    // existing getters/setters...
}
