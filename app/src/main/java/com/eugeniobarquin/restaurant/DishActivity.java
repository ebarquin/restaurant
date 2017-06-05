package com.eugeniobarquin.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DishActivity extends AppCompatActivity {

    protected static String TAG = DishActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

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
            Log.v(TAG, "Add dish option pressed");

            Intent menuListIntent = new Intent(this, MenuListActivity.class);
            startActivity(menuListIntent);
        }
        else if (item.getItemId() == R.id.menu_calculate) {
            Log.v(TAG, "Menu calculate option pressed");
        }

        return superReturn;
    }
}
