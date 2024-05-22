package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText mNameEt, mEmailEt, mPasswordEt, mPhoneEt;
    Button mRegisterBtn;
    TextView txtLogin;

    FirebaseAuth mAuth;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNameEt = (EditText) findViewById(R.id.name);
        mEmailEt = (EditText) findViewById(R.id.email);
        mPhoneEt = (EditText) findViewById(R.id.phone);
        mPasswordEt = (EditText) findViewById(R.id.password);
        mRegisterBtn = (Button) findViewById(R.id.reg_button);
        txtLogin = (TextView) findViewById(R.id.txt_login);

        mAuth = FirebaseAuth.getInstance();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Registering User...");
                pd.show();


                String email = mEmailEt.getText().toString().trim();
                String password = mPasswordEt.getText().toString().trim();

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);
                }
                else if(password.length() < 6)
                {
                    mPasswordEt.setError("Password length at least 6 characters");
                    mPasswordEt.setFocusable(true);
                }
                else
                {
                    registerUser(email, password);
                }
            }


        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void registerUser(String email, String password) {
        pd.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            pd.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();
                            String name = mNameEt.getText().toString();
                            String phone = mPhoneEt.getText().toString();
                            String email = user.getEmail();
                            String uid = user.getUid();

                            HashMap<Object, String> hashMap = new HashMap<>();
                            hashMap.put("email", email);
                            hashMap.put("uid", uid);
                            hashMap.put("name", name);
                            hashMap.put("phone", phone);
                            hashMap.put("image", " ");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);


                            Toast.makeText(RegisterActivity.this, "Regestered...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, ProfileActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
