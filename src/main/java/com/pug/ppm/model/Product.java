package com.pug.ppm.model;

/**
 * Created by Mike on 07/09/2017.
 */
public class Product {

    private String id;
    private String name;
    private int usdPrice;

    private Product() {
        // Damn you Jackson and always tripping me up with this
    }

    public Product(String id, String name, int usdPrice) {
        this.id = id;
        this.name = name;
        this.usdPrice = usdPrice;
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

    public int getUsdPrice() {
        return usdPrice;
    }

    public void setUsdPrice(int usdPrice) {
        this.usdPrice = usdPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", usdPrice=" + usdPrice +
                '}';
    }
}
