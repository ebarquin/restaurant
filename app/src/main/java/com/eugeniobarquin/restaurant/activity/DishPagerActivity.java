package com.eugeniobarquin.restaurant.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.fragment.DishFragment;
import com.eugeniobarquin.restaurant.model.Table;

public class DishPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        Table table = new Table();
        DishPagerAdapter adapter = new DishPagerAdapter(getFragmentManager(), table);

        pager.setAdapter(adapter);

    }

}

class DishPagerAdapter extends FragmentPagerAdapter {
    private Table mTable;

    public DishPagerAdapter(FragmentManager fm , Table table) {
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