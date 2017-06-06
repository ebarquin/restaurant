package com.eugeniobarquin.restaurant.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.fragment.DishFragment;
import com.eugeniobarquin.restaurant.model.Table;

public class DishPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);

    }

}

class CityPagerAdapter extends FragmentPagerAdapter {
    private Table mTable;

    public CityPagerAdapter(FragmentManager fm , Table table) {
        super(fm);
        mTable = table;
    }

    @Override
    public Fragment getItem(int position) {
        DishFragment fragment = new DishFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return mTable.getCount();
    }
}