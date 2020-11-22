package com.company;

public class Seller {
    private int sellerId;
    private String surname;
    private String name;

    public Seller(int id, String surname, String name) {
        this.sellerId = id;
        this.surname = surname;
        this.name = name;
    }

    public int getId() {
        return sellerId;
    }

    public void setId(int id) {
        this.sellerId = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + sellerId +
                " surname=" + surname +
                " name=" + name ;
    }
}
