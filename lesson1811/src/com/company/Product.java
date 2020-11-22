package com.company;

public class Product {
    private int productId;
    private String name;

    public Product(int id, String name) {
        this.productId = id;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + productId +
                ", name=" + name;
    }
}
