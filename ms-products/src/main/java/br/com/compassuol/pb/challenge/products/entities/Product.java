package br.com.compassuol.pb.challenge.products.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String description;
    private String name;
    private String imgUrl;
    private float price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product() {
        super();
    }

    public Product(String date, String description, String name, String imgUrl, float price) {
        super();
        this.date = date;
        this.description = description;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
  
    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    
}