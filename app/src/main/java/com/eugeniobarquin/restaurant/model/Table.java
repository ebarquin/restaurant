package com.eugeniobarquin.restaurant.model;


import com.eugeniobarquin.restaurant.R;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {
    private LinkedList<Dish> mDishes;
    private String mNumber;



    //Fake Table
    public Table() {
        mDishes = new LinkedList<>();
        mDishes.add(new Dish("Ensalada", R.drawable.ensalada, "Gluten", 12, "Poco hecho"));
        mDishes.add(new Dish("Paella", R.drawable.paella, "Cacahuetes", 7, "Al punto"));
        mDishes.add(new Dish("Pollo asado", R.drawable.pollo_asado, "Gluten", 6, "Muy hecho y sin patatas"));
        mDishes.add(new Dish("Sopa", R.drawable.sopa, "Gluten", 15, "Muy caliente"));
    }

    public Table(LinkedList<Dish> dishes, String number) {
        mDishes = dishes;
        mNumber = number;
    }

    public Dish getDish(int index) {
        return mDishes.get(index);
    }

    public int getCount() {
        return mDishes.size();
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }
    public void addDish(Dish dish) {
        mDishes.add(dish);
    }

    @Override
    public String toString() {
        return getNumber();
    }


}
