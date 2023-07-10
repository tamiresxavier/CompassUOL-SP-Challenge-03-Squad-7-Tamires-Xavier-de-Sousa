package br.com.compassuol.pb.challenge.products.dtos;

import java.util.Set;

public class ProductDTO {
    private String date;
    private String description;
    private String name;
    private String imgUrl;
    private float price;
    private Set<Long> categoryIds;

    public ProductDTO() {

    }

    public ProductDTO(String date, String description, String name, String imgUrl, float price) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.categoryIds = categoryIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
    
}
