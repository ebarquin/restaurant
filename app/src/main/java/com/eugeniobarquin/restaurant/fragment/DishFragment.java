package com.eugeniobarquin.restaurant.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eugeniobarquin.restaurant.activity.DishActivity;
import com.eugeniobarquin.restaurant.activity.MenuListActivity;
import com.eugeniobarquin.restaurant.model.Dish;
import com.eugeniobarquin.restaurant.R;


public class DishFragment extends Fragment{
    protected static final String ARG_TABLE = "table";

    protected static String TAG = DishActivity.class.getCanonicalName();
    private View mRoot;
    private Dish mDish;

    public static DishFragment newInstance (Dish dish) {
        DishFragment fragment = new DishFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_TABLE, dish);
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() !=null) {
            //Recibe model as argument
            mDish = (Dish) getArguments().getSerializable(ARG_TABLE);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRoot = inflater.inflate(R.layout.fragment_dish, container, false);


        setDish();


        return mRoot;
    }

    private void setDish() {
        //Get the views
        ImageView dishImage = (ImageView) mRoot.findViewById(R.id.dish_image);
        TextView dishName = (TextView) mRoot.findViewById(R.id.dish_name);
        TextView dishAlergens = (TextView) mRoot.findViewById(R.id.dish_alergens);
        TextView dishPrice = (TextView) mRoot.findViewById(R.id.dish_price);
        TextView dishNotes = (TextView) mRoot.findViewById(R.id.dish_notes);

        //Update view and model
        dishImage.setImageResource(mDish.getImage());
        dishName.setText(mDish.getName());
        dishAlergens.setText(mDish.getAlergens());
        dishPrice.setText(getString(R.string.dish_price_format, mDish.getPrice()));
        dishNotes.setText(mDish.getNotes());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_dish, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superReturn = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_add_dish) {
            Log.v(TAG, "Add dish option pressed");

            Intent menuListIntent = new Intent(getActivity(), MenuListActivity.class);
            startActivity(menuListIntent);
        }
        else if (item.getItemId() == R.id.menu_calculate) {
            Log.v(TAG, "Menu calculate option pressed");
        }



        return superReturn;
    }
}
