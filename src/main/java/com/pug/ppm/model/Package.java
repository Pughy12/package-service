package com.pug.ppm.model;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mike on 07/09/2017.
 */
public class Package {

    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private List<Product> products;
    private double price;

    private Package() {
        // Damn you Jackson and always tripping me up with this
    }

    public Package(String id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
        this.price = getPriceFromProducts(products);
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

    private static double getPriceFromProducts(List<Product> products) {
        return products.stream().collect(Collectors.summingDouble(Product::getUsdPrice));
    }

    @Override
    public String toString() {
        return "Package{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                ", price=" + price +
                '}';
    }
}
