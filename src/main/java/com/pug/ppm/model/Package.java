package com.pug.ppm.model;

import java.util.List;

/**
 * Created by Mike on 07/09/2017.
 */
public class Package {

    private String id;
    private String name;
    private String description;
    private List<Product> products;
    private double price;

    public Package(String id, String name, String description, List<Product> products, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
        this.price = price;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
