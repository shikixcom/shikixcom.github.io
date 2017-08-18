package com.example.mrsteve.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class TravelAgencies extends AppCompatActivity {

    Toolbar mToolbar;
    ListView lv;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_agencies);

        mToolbar = (Toolbar) findViewById(R.id.toolbar3);
        lv = (ListView) findViewById(R.id.listViewTravelAgencies);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mToolbar.setTitle(bundle.getString("TravelCountryName"));
            if (mToolbar.getTitle().toString().equalsIgnoreCase("China")){
                ArrayList<String> arrayAgensies = new ArrayList<>();
                arrayAgensies.addAll(Arrays.asList(getResources().getStringArray(R.array.Travel_agencies)));

                adapter = new ArrayAdapter<>(TravelAgencies.this,
                        android.R.layout.simple_list_item_1,
                        arrayAgensies);

                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (position == 0){
                            Intent intent = new Intent(TravelAgencies.this, PegasActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }
    }
}
