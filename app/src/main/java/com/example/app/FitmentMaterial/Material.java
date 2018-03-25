package com.example.app.FitmentMaterial;

import org.litepal.crud.DataSupport;

/**
 * Created by 1 on 2018/3/24.
 */

public class Material extends DataSupport {
    private int id;
    private String name;
    private String type;
    private double price;
    private String description;

    public Material(){}

    public Material(String name){
        this.name = name;
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
}
