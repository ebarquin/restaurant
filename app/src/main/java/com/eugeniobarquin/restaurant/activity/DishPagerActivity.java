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
import com.eugeniobarquin.restaurant.model.Restaurant;
import com.eugeniobarquin.restaurant.model.Table;

public class DishPagerActivity extends AppCompatActivity {

    public static final String EXTRA_TABLE = "EXTRA_TABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recibimos la mesa que queremos mostrar
        Table table = getIntent().getSerializableExtra(EXTRA_TABLE, ));

        //Añadimos si hace falta, el CityPagerFragment a nuestra jerarquía
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(R.id.fragment_dish_pager) == null) {
            DishPagerFragment fragment = DishPagerFragment.newInstance(table);
            fm.beginTransaction().add(R.id.fragment_dish_pager, fragment).commit();
        }

    }

}

