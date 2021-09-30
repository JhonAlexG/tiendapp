package com.example.tiendaapp;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String Name;
    private double Price;
    private String Description;
    private String urlImage;

    public Producto() {
    }

    public Producto(String name, double price, String urlImage) {

        Name = name;
        Price = price;
        this.urlImage = urlImage;
        this.Description = "Sin Descripción";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Name+" ("+Price+")";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
