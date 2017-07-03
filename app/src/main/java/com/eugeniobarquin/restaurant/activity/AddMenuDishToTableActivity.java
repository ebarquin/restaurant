package com.eugeniobarquin.restaurant.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.MenuDish;

public class AddMenuDishToTableActivity extends AppCompatActivity {

    public static final String EXTRA_MENU_DISH = "EXTRA_MENU_DISH";
    private MenuDish menuDish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_dish_to_table);

        menuDish = (MenuDish) getIntent().getSerializableExtra(EXTRA_MENU_DISH);
        Log.d("***********", menuDish.getName());

        setUpViews();
    }

    private void setUpViews() {

        //Get views

        ImageView dishImage = (ImageView) findViewById(R.id.dish_image);
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView dishAlergens = (TextView) findViewById(R.id.dish_alergens);
        TextView dishPrice = (TextView) findViewById(R.id.dish_price);
        TextView dishNotes = (TextView) findViewById(R.id.dish_notes);

        //Update view and model
        dishImage.setImageResource(menuDish.getImage());
        dishName.setText(menuDish.getName());
        dishAlergens.setText(menuDish.getAlergens());
        dishPrice.setText(getString(R.string.dish_price_format, menuDish.getPrice()));
        dishNotes.setText(menuDish.getNotes());
    }
}
