package com.eugeniobarquin.restaurant.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.MenuDish;
import com.eugeniobarquin.restaurant.model.MenuRestaurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;


public class MenuListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView list = (ListView) findViewById(R.id.list_menu);

        setContentView(R.layout.activity_menu_list);

        //Access to model
        final AsyncTask<Void, Integer, LinkedList<MenuDish>> menuRestaurantDownloader = new AsyncTask<Void, Integer, LinkedList<MenuDish>>() {

            @Override
            protected LinkedList<MenuDish> doInBackground(Void... params) {
                return downloadMenu();
            }

            @Override
            protected void onPostExecute(LinkedList<MenuDish> menuDishes) {
                super.onPostExecute(menuDishes);

                ListView list = (ListView) findViewById(R.id.list_menu);

                ArrayAdapter<MenuDish> adapter = new ArrayAdapter<MenuDish>(MenuListActivity.this,android.R.layout.simple_list_item_1, menuDishes);

                //Draw ViewList
                list.setAdapter(adapter);

            }


        };
        menuRestaurantDownloader.execute();

    }

    private LinkedList<MenuDish> downloadMenu() {
        URL url = null;
        InputStream input = null;

        try {
            url = new URL(String.format("http://www.mocky.io/v2/593dae651100005b1e722af5"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            byte data[] = new byte[1024];
            int downloadedBytes;
            input = con.getInputStream();
            StringBuilder sb = new StringBuilder();
            while ((downloadedBytes = input.read(data)) != -1) {
                sb.append(new String(data, 0,downloadedBytes));
            }

            //Transform Json into code
            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray list = jsonRoot.getJSONArray("dish");

            LinkedList<MenuDish> menu = new LinkedList<MenuDish>();


            for(int i=0; i < list.length(); i++) {

                JSONObject dish = list.getJSONObject(i);

                String name = dish.getString("name");
                String imageString = dish.getString("image");
                String allergens = dish.getString("allergens");
                float price = (float) dish.getDouble("price");
                String notes = dish.getString("notes");

                //Convert imageString to drawable
                int imageResource = R.drawable.ensalada;
                switch (imageString) {
                    case "paella": imageResource = R.drawable.paella; break;
                    case "ensalada": imageResource = R.drawable.ensalada; break;
                    case "pollo_asado": imageResource = R.drawable.pollo_asado; break;
                    case "sopa": imageResource = R.drawable.sopa; break;


                }
                menu.add(new MenuDish(name, imageResource, allergens, price, notes));

            }

            return menu;



        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return null;
    }

}

