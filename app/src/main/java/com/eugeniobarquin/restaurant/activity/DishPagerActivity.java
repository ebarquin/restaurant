package com.eugeniobarquin.restaurant.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.fragment.DishFragment;
import com.eugeniobarquin.restaurant.fragment.DishPagerFragment;
import com.eugeniobarquin.restaurant.model.Dish;
import com.eugeniobarquin.restaurant.model.Table;

import java.util.LinkedList;

public class DishPagerActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE = "EXTRA_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinkedList<Dish> fakeDish = new LinkedList<>();
        Table fakeTable = new Table(fakeDish, "Mesa Fake");

        //Recibimos el indice de la mesa que queremos mostrar
        Table table = getIntent().getSerializableExtra(EXTRA_TABLE, fakeTable);
        //Table tableIndex = getIntent().getSerializableExtra(EXTRA_TABLE, new Table());

        //Añadimos si hace falta, el CityPagerFragment a nuestra jerarquía
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_pager) == null) {
            DishPagerFragment fragment = new DishPagerFragment();
            fm.beginTransaction().add(R.id.fragment_dish_pager, fragment).commit();
        }

    }

}

