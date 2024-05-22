package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

public class activityThree extends AppCompatActivity {

    ActionBar actionBar;
    SearchView searchView;
    ListView stations;
    String[] menuItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Access Live Status for Specific Stations");

        searchView = (SearchView) findViewById(R.id.searchStations);
        stations = (ListView) findViewById(R.id.allStationsList);
        menuItems = getResources().getStringArray(R.array.allStationsList);

        adapter = new ArrayAdapter<String>(activityThree.this, R.layout.sample_view, R.id.textId, menuItems);
        stations.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        stations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = (String) parent.getItemAtPosition(position);

                if(true){
                    Intent intent = new Intent(activityThree.this,ShowPostActivity.class);
                    intent.putExtra("menuItems",selectedText);
                    startActivity(intent);
                }

            }
        });



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
