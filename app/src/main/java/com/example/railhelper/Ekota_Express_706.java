package com.example.railhelper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Ekota_Express_706 extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();
    //String[] trainList;
    ArrayAdapter<String> adapter;

    List<String> namesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekota__express_706);

        //adapter = new ArrayAdapter<String>(this,R.layout.sample_view,R.id.textId,arrayList);

        listView = (ListView) findViewById(R.id.Ekota_706_stations);

        //listView.setAdapter(adapter);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Trains.Ekota 705.stations.Dhaka").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                namesList.clear();
                for(DocumentSnapshot snapshot : queryDocumentSnapshots){
                    namesList.add(snapshot.getString("reaches"));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,namesList);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
        });
    }
}
