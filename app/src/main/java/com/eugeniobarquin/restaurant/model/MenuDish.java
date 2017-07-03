package com.eugeniobarquin.restaurant.model;


import java.io.Serializable;

public class MenuDish implements Serializable {
    private String mName;
    private int mImage;
    private String mAlergens;
    private float mPrice;
    private String mNotes;

    public MenuDish(String name, int image, String alergens, float price, String notes) {
        mName = name;
        mImage = image;
        mAlergens = alergens;
        mPrice = price;
        mNotes = notes;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getAlergens() {
        return mAlergens;
    }

    public void setAlergens(String alergens) {
        mAlergens = alergens;
    }

    public Float getPrice() {
        return mPrice;
    }

    public void setPrice(Float price) {
        mPrice = price;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mName = notes;
    }

    @Override
    public String toString() {
        return getName();
    }
}


