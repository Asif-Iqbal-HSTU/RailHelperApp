package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference databaseReference;

    //TextView mProfileTv;
    TextView emailTv, nameTv, phoneTv;
    ImageView avatarIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //firebaseAuth = FirebaseAuth.getInstance();
        //user = firebaseAuth.getCurrentUser();
        checkUserStatus();


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }



    private void checkUserStatus()
    {
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if(user != null)
        {
            //emailTv.setText(user.getEmail());
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Users");

            emailTv = findViewById(R.id.emailTv);
            nameTv = findViewById(R.id.nameTv);
            phoneTv = findViewById(R.id.phoneTv);
            avatarIv = findViewById(R.id.avatarIv);

            Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        String name = ""+ ds.child("name").getValue();
                        String email = ""+ ds.child("email").getValue();
                        String phone = ""+ ds.child("phone").getValue();
                        String image = ""+ ds.child("image").getValue();

                        nameTv.setText(name);
                        emailTv.setText(email);
                        phoneTv.setText(phone);
                        try {
                            Picasso.get().load(image).into(avatarIv);
                        }
                        catch (Exception e){
                            Picasso.get().load(R.drawable.ic_add_image).into(avatarIv);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            startActivity(new Intent(ProfileActivity.this, RegisterActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflating menu
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout)
        {
            firebaseAuth.signOut();
            checkUserStatus();
        }
        else if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
