package com.eugeniobarquin.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DishActivity extends AppCompatActivity {

    protected static String TAG = DishActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        //Fake model
        Dish dish = new Dish("Ensalada", R.drawable.ensalada, "Gluten", 12);
        setDish(dish);


    }

    private void setDish(Dish dish) {
        //Get the views
        ImageView dishImage = (ImageView) findViewById(R.id.dish_image);
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView dishAlergens = (TextView) findViewById(R.id.dish_alergens);
        TextView dishPrice = (TextView) findViewById(R.id.dish_price);

        //Update view and model
        dishImage.setImageResource(dish.getImage());
        dishName.setText(dish.getName());
        dishAlergens.setText(dish.getAlergens());
        dishPrice.setText(getString(R.string.dish_price_format, dish.getPrice()));
    }
}
