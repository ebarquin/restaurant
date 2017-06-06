package com.eugeniobarquin.restaurant.model;


import com.eugeniobarquin.restaurant.R;

import java.io.Serializable;
import java.util.LinkedList;

public class Table implements Serializable {
    private LinkedList<Dish> mDishes;

    //Fake Table
    public Table() {
        mDishes = new LinkedList<>();
        mDishes.add(new Dish("Ensalada", R.drawable.ensalada, "Gluten", 12, "Poco hecho"));
        mDishes.add(new Dish("Paella", R.drawable.paella, "Cacahuetes", 7, "Al punto"));
        mDishes.add(new Dish("Pollo asado", R.drawable.pollo_asado, "Gluten", 6, "Muy hecho y sin patatas"));
        mDishes.add(new Dish("Sopa", R.drawable.sopa, "Gluten", 15, "Muy caliente"));
    }

    public Dish getDish(int index) {
        return mDishes.get(index);
    }

    public int getCount() {
        return mDishes.size();
    }

}
