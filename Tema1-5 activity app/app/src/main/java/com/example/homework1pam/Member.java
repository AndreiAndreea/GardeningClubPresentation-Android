package com.example.homework1pam;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String name;
    private String imageUrl;
    private String shortDescription;
    private String detailedDescription;

    // Default constructor
    public Member() {
    }

    // Constructor without ID (useful when adding new members, as ID is auto-generated)
    public Member(String name, String imageUrl, String shortDescription, String detailedDescription) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
    }

    // Full constructor
    public Member(int id, String name, String imageUrl, String shortDescription, String detailedDescription) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.detailedDescription = detailedDescription;
    }

    //getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getShortDescription() {
        return shortDescription;
    }
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    public String getDetailedDescription() {
        return detailedDescription;
    }
    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
}
