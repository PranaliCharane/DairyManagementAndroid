package com.sunbeaminfo.mobilestore.entity;

import java.io.Serializable;

public class ProductDetails implements Serializable {


    private int id;
    private String name;
    private double price;
    private String description;
    private String company;
    private String image;


    public ProductDetails(){

    }

    public ProductDetails(int id, String name, double price, String description, String company, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.company = company;
        this.image = image;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", company='" + company + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}