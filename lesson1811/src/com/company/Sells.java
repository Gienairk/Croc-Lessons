package com.company;

import java.time.LocalDate;
import java.util.Date;

public class Sells {
    private int sellId;
    private int sellerId;
    private int productId;
    private int numberOfProducts;
    private LocalDate date;

    public Sells(int sellId, int sellerId, int productId, int numberOfProducts, LocalDate date) {
        this.sellId = sellId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.numberOfProducts = numberOfProducts;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sells{" +
                "sellId=" + sellId +
                ", sellerId=" + sellerId +
                ", productId=" + productId +
                ", numberOfProducts=" + numberOfProducts +
                ", date=" + date +
                '}';
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
