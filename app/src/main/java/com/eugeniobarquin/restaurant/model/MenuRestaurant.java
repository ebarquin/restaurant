package com.eugeniobarquin.restaurant.model;


import java.util.LinkedList;

public class MenuRestaurant {

    private LinkedList<MenuDish> mMenuDishes;

    public MenuRestaurant(LinkedList<MenuDish> menuDishes) {
        mMenuDishes = menuDishes;
    }

}
