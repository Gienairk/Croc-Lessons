package com.company;

public class Warehouse {
    private int id;
    private int productid;
    private int cost;
    private int number;

    public Warehouse(int id, int productid, int cost, int number) {
        this.id = id;
        this.productid = productid;
        this.cost = cost;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", productid=" + productid +
                ", cost=" + cost +
                ", number=" + number +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
