package com.pug.ppm.model;

/**
 * Created by Mike on 07/09/2017.
 */
public class Product {

    private String id;
    private String name;
    private int price;

    public Product(String id, String name, int usdPrice) {
        this.id = id;
        this.name = name;
        this.price = usdPrice;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
