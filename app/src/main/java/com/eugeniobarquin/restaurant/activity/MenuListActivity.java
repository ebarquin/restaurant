package com.eugeniobarquin.restaurant.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.MenuDish;
import com.eugeniobarquin.restaurant.model.MenuRestarurant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MenuListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView list = (ListView) findViewById(R.id.list_menu);

        setContentView(R.layout.activity_menu_list);

        //Access to model
        final AsyncTask<Void, Integer, MenuDish> menuRestaurantDownloader = new AsyncTask<Void, Integer, MenuDish>() {

            @Override
            protected MenuDish doInBackground(Void... params) {
                return downloadMenu();
            }

            @Override
            protected void onPostExecute(MenuDish menuDish) {
                super.onPostExecute(menuDish);

                //Draw ViewList

            }


        };
        menuRestaurantDownloader.execute();

    }

    private MenuDish downloadMenu() {
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

            JSONObject jsonRoot = new JSONObject(sb.toString());
            JSONArray list = jsonRoot.getJSONArray("dish");
            JSONObject dish = list.getJSONObject(0);

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
            return new MenuDish(name, imageResource,allergens,price,notes);


        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return null;
    }

}

