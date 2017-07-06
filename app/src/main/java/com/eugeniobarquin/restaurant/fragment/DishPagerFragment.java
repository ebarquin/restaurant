package com.eugeniobarquin.restaurant.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eugeniobarquin.restaurant.R;

import com.eugeniobarquin.restaurant.activity.MenuListActivity;
import com.eugeniobarquin.restaurant.model.Dish;
import com.eugeniobarquin.restaurant.model.Table;

import java.util.LinkedList;

import static android.content.ContentValues.TAG;

public class DishPagerFragment extends Fragment {

    private static final String ARG_TABLE2 = "ARG_TABLE";

    private ViewPager mPager;
    private Table mTable;

    public static DishPagerFragment newInstance(Table table) {
        DishPagerFragment fragment = new DishPagerFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE2, table);
        fragment.setArguments(arguments);

        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


        if (getArguments() != null) {
            mTable = (Table) getArguments().getSerializable(ARG_TABLE2);
        }
        
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_dish_pager,container, false);

        mPager = (ViewPager) root.findViewById(R.id.view_pager);

        //mTable = new Table();
        DishPagerAdapter adapter = new DishPagerAdapter(getFragmentManager(), mTable);

        mPager.setAdapter(adapter);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_dish_pager, menu);
        inflater.inflate(R.menu.menu_dish, menu);
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
        }else if (item.getItemId() == R.id.menu_add_dish) {
            Log.v(TAG, "Add dish option pressed");

            Intent menuListIntent = new Intent(getActivity(), MenuListActivity.class);
            menuListIntent.putExtra(MenuListActivity.EXTRA_TABLE, mTable);
            startActivity(menuListIntent);
        } else if (item.getItemId() == R.id.menu_calculate) {
            Log.v(TAG, "Menu calculate option pressed");
            Float ticket = 0f ;
            for(int i = 0; i < mTable.getCount(); i++) {
                Dish dish = mTable.getDish(i);
                ticket = ticket + dish.getPrice();

            }

            Toast.makeText(getView().getContext(), "Ticket = " + ticket, Toast.LENGTH_LONG).show();

        }


        return superReturn;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
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