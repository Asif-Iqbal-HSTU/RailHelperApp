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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activityTwo extends AppCompatActivity {

    ListView listView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    //String[] trainList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trains");

        adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textId,arrayList);

        listView = (ListView) findViewById(R.id.allTrainList);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Trains");

        listView.setAdapter(adapter);

        Query query = databaseReference.orderByChild("zone").equalTo("east");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String name = ""+ ds.child("name").getValue();
                    arrayList.add(name);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedText = (String) parent.getItemAtPosition(position);
                String S = null;

                Class classToBeOpen = null;

                if(selectedText.equals("705 Ekota Express")){
                    S = "Ekota_Express_705";
                    Intent intent = new Intent(activityTwo.this, Trains.class);
                    //intent.putExtra("menuItems",value);
                    startActivity(intent);
                }
                else if(selectedText.equals("706 Ekota Express")){

                    Intent intent = new Intent(activityTwo.this, Ekota_Express_706.class);
                    //intent.putExtra("menuItems",value);
                    startActivity(intent);
                }

                /*try{
                    classToBeOpen = Class.forName("com.example.railhelper.trains." + S);
                }
                catch (ClassNotFoundException e){
                    e.printStackTrace();
                }*/


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
