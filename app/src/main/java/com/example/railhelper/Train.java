package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Train extends AppCompatActivity {

    ListView listView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    //String[] trainList;
    ArrayAdapter<String> adapter;
    SearchView searchView;
    String zoneName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);

        searchView = (SearchView) findViewById(R.id.searchTrain);

        Bundle bundle = getIntent().getExtras();
        zoneName = bundle.getString("zoneName");
        //trainName = value;
        setTitle(zoneName+" Trains");

        adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textId,arrayList);

        listView = (ListView) findViewById(R.id.allTrainList);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });*/
        databaseReference = firebaseDatabase.getReference("Trains").child(zoneName);

        listView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    arrayList.add(ds.getKey());
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

                if(true){
                    Intent intent = new Intent(Train.this, Station.class);
                    Bundle extras = new Bundle();
                    extras.putString("zoneName",zoneName);
                    extras.putString("trainName",selectedText);
                    intent.putExtras(extras);
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
