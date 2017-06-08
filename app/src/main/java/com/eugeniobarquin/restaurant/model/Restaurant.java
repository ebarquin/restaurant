package com.eugeniobarquin.restaurant.model;

import java.util.LinkedList;



public class Restaurant {

    private LinkedList <Table> mRestaurant;

    public Restaurant() {
        mRestaurant = new LinkedList<>();
        Table table1 = new Table();
        Table table2 = new Table();
        Table table3 = new Table();
        Table table4 = new Table();
        mRestaurant.add(table1);
        mRestaurant.add(table2);
        mRestaurant.add(table3);
        mRestaurant.add(table4);
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
