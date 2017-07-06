package com.eugeniobarquin.restaurant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.Dish;
import com.eugeniobarquin.restaurant.model.MenuDish;
import com.eugeniobarquin.restaurant.model.Restaurant;
import com.eugeniobarquin.restaurant.model.Table;

public class AddMenuDishToTableActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE = "table";
    public static final String EXTRA_DISH = "dish";
    private MenuDish menuDish;
    private Dish mDish;
    private String mMenuNotes;
    private TextView dishNotes;
    private Table mtable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mtable = (Table) getIntent().getSerializableExtra(EXTRA_TABLE);
        menuDish = (MenuDish) getIntent().getSerializableExtra(EXTRA_DISH);
        setContentView(R.layout.activity_add_menu_dish_to_table);



        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_add_menu_dish_to_table_activity, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_save_dish_to_table) {
            addMenudishToTable();


        }

        return superReturn;
    }

    private void setUpViews() {

        //Get views

        ImageView dishImage = (ImageView) findViewById(R.id.dish_image);
        TextView dishName = (TextView) findViewById(R.id.dish_name);
        TextView dishAlergens = (TextView) findViewById(R.id.dish_alergens);
        TextView dishPrice = (TextView) findViewById(R.id.dish_price);
        dishNotes = (TextView) findViewById(R.id.dish_notes);

        //Update view and model
        dishImage.setImageResource(menuDish.getImage());
        dishName.setText(menuDish.getName());
        dishAlergens.setText(menuDish.getAlergens());
        dishPrice.setText(getString(R.string.dish_price_format, menuDish.getPrice()));
        dishNotes.setText("");
    }

    private void addMenudishToTable() {
        mMenuNotes =  dishNotes.getText().toString();
        mDish = new Dish(menuDish.getName(),menuDish.getImage(),menuDish.getAlergens(),menuDish.getPrice(),mMenuNotes);

        Restaurant restaurant = Restaurant.getInstance();
        String number = mtable.getNumber();
        Table table = restaurant.getInstance().getTable(Integer.parseInt(number)-1);
        table.addDish(mDish);

        Intent intent = new Intent(this, DishActivity.class );
        startActivity(intent);


    }
}
