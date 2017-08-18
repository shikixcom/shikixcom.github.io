package com.example.mrsteve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class cityActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ListView lv;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);


        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        lv = (ListView) findViewById(R.id.listViewCity);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("CountryName"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("Russia")){
                ArrayList<String> arrayCity = new ArrayList<>();
                arrayCity.addAll(Arrays.asList(getResources().getStringArray(R.array.array_city_Russia)));

                adapter = new ArrayAdapter<>(
                        cityActivity.this,
                        android.R.layout.simple_list_item_1,
                        arrayCity);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int i, long l) {
                        Intent intent = new Intent(cityActivity.this, TravelCountry.class);
                        intent.putExtra("CityName", lv.getItemAtPosition(i).toString());
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
