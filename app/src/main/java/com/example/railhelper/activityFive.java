package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class activityFive extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3, button4, button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button1){
            String s = "tel:" + "999";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            startActivity(intent);
        }
        else if (view.getId() == R.id.button2){
            String s = "tel:" + "333";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            startActivity(intent);

        }
        else if (view.getId() == R.id.button3){
            String s = "tel:" + "01712934373";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            startActivity(intent);

        }
        else if (view.getId() == R.id.button4){
            String s = "tel:" + "16263";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            startActivity(intent);

        }
        else if (view.getId() == R.id.button5){
            String s = "tel:" + "9555555";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse(s));
            startActivity(intent);
        }
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
