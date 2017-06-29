package com.eugeniobarquin.restaurant.model;


import java.util.LinkedList;

public class MenuRestarurant {

    private LinkedList<MenuDish> mMenuDishes;

    public MenuRestarurant(LinkedList<MenuDish> menuDishes) {
        mMenuDishes = menuDishes;
    }

}
