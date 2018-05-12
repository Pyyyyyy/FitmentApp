package com.example.app.FitmentMaterial;

import android.graphics.Bitmap;


/**
 * Created by 1 on 2018/3/24.
 */

public class Material {
    private int id;
    private String name;
    private String type;
    private double price;
    private String description;
    private Bitmap picture;


    public Material(String name,Bitmap picture){
        this.name = name;
        this.picture = picture;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

}
