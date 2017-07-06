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



public class DishActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        //Load Fragment
        FragmentManager fm = getFragmentManager();

        //Check if fragment is already loaded
        if (fm.findFragmentById(R.id.table_list_fragment) == null) {
            //It's not loaded
            Restaurant restaurant = Restaurant.getInstance();
            TableListFragment fragment = TableListFragment.newInstance(restaurant.getTables());

            fm.beginTransaction().add(R.id.table_list_fragment, fragment).commit();
        }

    }

    @Override
    public void onTableSelected(Table table) {
        Intent intent = new Intent(this, DishPagerActivity.class );
        intent.putExtra(DishPagerActivity.EXTRA_TABLE, table);
        startActivity(intent);
    }




}
