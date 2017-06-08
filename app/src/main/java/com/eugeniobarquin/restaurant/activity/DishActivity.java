package com.eugeniobarquin.restaurant.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.fragment.TableListFragment;
import com.eugeniobarquin.restaurant.model.Restaurant;
import com.eugeniobarquin.restaurant.model.Table;

public class DishActivity extends AppCompatActivity {

    protected static String TAG = DishActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        //Load Fragment
        FragmentManager fm = getFragmentManager();

        //Check if fragment is already loaded
        if (fm.findFragmentById(R.id.table_list_fragment) == null) {
            //It's not loaded
            Restaurant restaurant = new Restaurant();
            TableListFragment fragment = TableListFragment.newInstance(restaurant.getTables());

            fm.beginTransaction().add(R.id.table_list_fragment, fragment).commit();
        }

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
