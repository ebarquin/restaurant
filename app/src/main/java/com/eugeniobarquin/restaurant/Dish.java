package com.eugeniobarquin.restaurant;


public class Dish {

    private String mName;
    private int mImage;
    private String mAlergens;
    private float mPrice;

    public Dish(String name, int image, String alergens, float price) {
        mName = name;
        mImage = image;
        mAlergens = alergens;
        mPrice = price;
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
}
