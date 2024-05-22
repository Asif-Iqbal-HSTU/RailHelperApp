package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class AddPostActivity extends AppCompatActivity {

    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference userDbRef;

    EditText titleEt, descriptionEt;
    Button uploadBtn;

    String name, email, uid, value;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Bundle bundle = getIntent().getExtras();
        value = bundle.getString("menuItems");
        setTitle(value);

        actionBar = getSupportActionBar();
        actionBar.setTitle(value);

        pd = new ProgressDialog(this);

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();
        checkUserStatus();

        actionBar.setSubtitle(email);


    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserStatus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkUserStatus();
    }

    private void checkUserStatus()
    {
        //FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null)
        {
            email = user.getEmail();
            uid = user.getUid();

            userDbRef = FirebaseDatabase.getInstance().getReference("Users");
            Query query = userDbRef.orderByChild("email").equalTo(email);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        name = ""+ds.child("name").getValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {



                }
            });

            titleEt = (EditText) findViewById(R.id.pTitleEt);
            descriptionEt = (EditText) findViewById(R.id.pDescriptionEt);
            uploadBtn = (Button) findViewById(R.id.pUploadBtn);

            uploadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = titleEt.getText().toString().trim();
                    String description = descriptionEt.getText().toString().trim();

                    if(TextUtils.isEmpty(title)){
                        Toast.makeText(AddPostActivity.this, "Enter Title...", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(TextUtils.isEmpty(description)){
                        Toast.makeText(AddPostActivity.this, "Enter Description...", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        uploadData(title, description);
                    }
                }


            });
        }
        else
        {
            startActivity(new Intent(AddPostActivity.this, RegisterActivity.class));
            finish();
        }
    }

    private void uploadData(String title, String description) {
        pd.setMessage("Publishing Post");
        pd.show();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String filePathAndName = "Posts/" + "post_" + timeStamp;

        //upload section
        //StorageReference ref = FirebaseStorage.getInstance().getReference().child(filePathAndName);

        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("uid", uid);
        hashMap.put("uName", name);
        hashMap.put("uEmail", email);
        hashMap.put("pId", timeStamp);
        hashMap.put("pTitle", title);
        hashMap.put("pDescr", description);
        hashMap.put("pTime", timeStamp);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts/"+value);
        ref.child(timeStamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        pd.dismiss();
                        Toast.makeText(AddPostActivity.this, "Post Published", Toast.LENGTH_SHORT).show();
                        titleEt.setText("");
                        descriptionEt.setText("");

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(AddPostActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
