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
import com.eugeniobarquin.restaurant.model.Table;

public class DishPagerActivity extends AppCompatActivity {

    private ViewPager mPager;
    private Table mTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_pager);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPager = (ViewPager) findViewById(R.id.view_pager);
        mTable = new Table();
        DishPagerAdapter adapter = new DishPagerAdapter(getFragmentManager(), mTable);

        mPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_dish_pager, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.previous) {
            //Move pager backward
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);

            return true;
        }
        else if (item.getItemId() == R.id.next) {
            //Move pager fordward
            mPager.setCurrentItem(mPager.getCurrentItem() + 1);

            return true;
        }

        return superReturn;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem menuPrev = menu.findItem(R.id.previous);
        MenuItem menuNext = menu.findItem(R.id.next);

        if (mPager.getCurrentItem() > 0) {
            //I can move backward
            menuPrev.setEnabled(true);
        }
        else {
            menuPrev.setEnabled(false);
        }

        if (mPager.getCurrentItem() < mTable.getCount() - 1 ) {
            //I can move fordward
            menuNext.setEnabled(true);
        }
        else {
            menuNext.setEnabled(false);
        }
        return true;
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
        DishFragment fragment = DishFragment.newInstance(mTable.getDish(position));

        return fragment;
    }

    @Override
    public int getCount() {
        return mTable.getCount();
    }
}