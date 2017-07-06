package com.eugeniobarquin.restaurant.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import com.eugeniobarquin.restaurant.R;
import com.eugeniobarquin.restaurant.model.MenuDish;
import com.eugeniobarquin.restaurant.model.Table;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;


public class MenuListActivity extends AppCompatActivity {
    public static final String EXTRA_TABLE = "table";
    private static final int LOADING_VIEW_INDEX = 1;
    private static final int MENU_LIST_INDEX = 0;
    private Table mtable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mtable = (Table) getIntent().getSerializableExtra(EXTRA_TABLE);

        ListView list = (ListView) findViewById(R.id.list_menu);

        setContentView(R.layout.activity_menu_list);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.view_switcher);
        viewSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        viewSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        //Access to model
        final AsyncTask<Void, Integer, LinkedList<MenuDish>> menuRestaurantDownloader = new AsyncTask<Void, Integer, LinkedList<MenuDish>>() {

            @Override
            protected LinkedList<MenuDish> doInBackground(Void... params) {
                return downloadMenu();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                viewSwitcher.setDisplayedChild(LOADING_VIEW_INDEX);
            }

            @Override
            protected void onPostExecute(LinkedList<MenuDish> menuDishes) {
                super.onPostExecute(menuDishes);

                ListView list = (ListView) findViewById(R.id.list_menu);

                final ArrayAdapter<MenuDish> adapter = new ArrayAdapter<MenuDish>(MenuListActivity.this,android.R.layout.simple_list_item_1, menuDishes);

                //Draw ViewList
                list.setAdapter(adapter);

                viewSwitcher.setDisplayedChild(MENU_LIST_INDEX);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        MenuDish menuDish = adapter.getItem(i);

                        Intent intent = new Intent(view.getContext(), AddMenuDishToTableActivity.class );
                        intent.putExtra(AddMenuDishToTableActivity.EXTRA_DISH, menuDish);
                        intent.putExtra(MenuListActivity.EXTRA_TABLE, mtable);
                        startActivity(intent);
                    }
                });
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


                //Convert imageString to drawable
                int imageResource = R.drawable.ensalada;
                switch (imageString) {
                    case "paella": imageResource = R.drawable.paella; break;
                    case "ensalada": imageResource = R.drawable.ensalada; break;
                    case "pollo_asado": imageResource = R.drawable.pollo_asado; break;
                    case "sopa": imageResource = R.drawable.sopa; break;


                }
                menu.add(new MenuDish(name, imageResource, allergens, price));

            }

            return menu;



        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return null;
    }




}

