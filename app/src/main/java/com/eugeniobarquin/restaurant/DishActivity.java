package com.eugeniobarquin.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DishActivity extends AppCompatActivity {

    protected static String TAG = DishActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        //Fake model
        Dish dish = new Dish("Ensalada", R.drawable.ensalada, "Gluten", 12, "Poco hecho");
        setDish(dish);


    }

    private void setDish(Dish dish) {
        //Get the views
        ImageView dishImage = (ImageView) findViewById(R.id.dish_image);
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView dishAlergens = (TextView) findViewById(R.id.dish_alergens);
        TextView dishPrice = (TextView) findViewById(R.id.dish_price);
        TextView dishNotes = (TextView) findViewById(R.id.dish_notes);

        //Update view and model
        dishImage.setImageResource(dish.getImage());
        dishName.setText(dish.getName());
        dishAlergens.setText(dish.getAlergens());
        dishPrice.setText(getString(R.string.dish_price_format, dish.getPrice()));
        dishNotes.setText(dish.getNotes());
    }
    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_dish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_add_dish) {
            Log.v(TAG, "Add dish option pulsed");
        }
        else if (item.getItemId() == R.id.menu_calculate) {
            Log.v(TAG, "Menu calculate option pulsed");
        }

        return superReturn;
    }
}
