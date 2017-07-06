package com.eugeniobarquin.restaurant.model;

import android.support.design.widget.TabLayout;

import com.eugeniobarquin.restaurant.R;

import java.util.LinkedList;



public class Restaurant {

    private static Restaurant mInstance;


    private LinkedList <Table> mRestaurant;

    //Singleton
    public static Restaurant getInstance(){
        if (mInstance == null) {
            //There is no static instance  of Restaurant
            mInstance = new Restaurant();
        }
        return mInstance;
    }

    public Restaurant() {
        mRestaurant = new LinkedList<>();

        //Table 1
        LinkedList table1Dishes = new LinkedList();
        table1Dishes.add(new Dish("Ensalada", R.drawable.ensalada, "Gluten", 12, "Poco hecho"));

        //Table 2
        LinkedList table2Dishes = new LinkedList();
        table2Dishes.add(new Dish("Paella", R.drawable.paella, "Cacahuetes", 7, "Al punto"));
        table2Dishes.add(new Dish("Pollo asado", R.drawable.pollo_asado, "Gluten", 6, "Muy hecho y sin patatas"));

        //Table 3
        LinkedList table3Dishes = new LinkedList();
        table3Dishes.add(new Dish("Sopa", R.drawable.sopa, "Gluten", 15, "Muy caliente"));
        //Table 4
        LinkedList table4Dishes = new LinkedList();

        mRestaurant.add(new Table(table1Dishes, "Mesa 1"));
        mRestaurant.add(new Table(table2Dishes, "Mesa 2"));
        mRestaurant.add(new Table(table3Dishes, "Mesa 3"));
        mRestaurant.add(new Table(table4Dishes, "Mesa 4"));
    }

    public Table getTable(int index) {
        return mRestaurant.get(index);
    }

    public int getCount() {
        return mRestaurant.size();
    }

    public LinkedList<Table> getTables() {
        return mRestaurant;
    }
}
