package com.example.railhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Zone extends AppCompatActivity implements View.OnClickListener {


    private EditText nameEditText;
    private Button buttonEast, buttonWest, searchButton;
    Mydbhelper mydbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        //Edited by Galib
        mydbhelper = new Mydbhelper(this);
        SQLiteDatabase db = mydbhelper.getWritableDatabase();
        buttonEast = findViewById(R.id.buttonEast);
        buttonWest = findViewById(R.id.buttonWest);
        nameEditText = (EditText) findViewById(R.id.searchId);
        searchButton = (Button) findViewById(R.id.searchButton);
        buttonEast.setOnClickListener(this);
        buttonWest.setOnClickListener(this);
        searchButton.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {

        String name = nameEditText.getText().toString();
        if (view.getId() == R.id.buttonEast){
            Cursor cursor = mydbhelper.display1();
            if (cursor.getCount() == 0){
                showData("Error","Nothing to show");
            }

            else{
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()){
                    stringBuffer.append("Train no: "+cursor.getString(0) + "\n");
                    stringBuffer.append("Train Name: "+cursor.getString(1) + "\n");
                    stringBuffer.append("Weekly Holiday: "+cursor.getString(2) + "\n");
                    stringBuffer.append("Initial Station: "+cursor.getString(3) + "\n");
                    stringBuffer.append("Start Time: "+cursor.getString(4) + "\n");
                    stringBuffer.append("Destination Station: "+cursor.getString(5) + "\n");
                    stringBuffer.append("End Time: "+cursor.getString(6) + "\n\n");
                }
                showData("EastZone Trains", stringBuffer.toString());
            }
        }

        else if (view.getId() == R.id.buttonWest){
            Cursor cursor = mydbhelper.display2();
            if (cursor.getCount() == 0){
                showData("Error","Nothing to show");
            }

            else{
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()){
                    stringBuffer.append("Train no: "+cursor.getString(0) + "\n");
                    stringBuffer.append("Train Name: "+cursor.getString(1) + "\n");
                    stringBuffer.append("Weekly Holiday: "+cursor.getString(2) + "\n");
                    stringBuffer.append("Initial Station: "+cursor.getString(3) + "\n");
                    stringBuffer.append("Start Time: "+cursor.getString(4) + "\n");
                    stringBuffer.append("Destination Station: "+cursor.getString(5) + "\n");
                    stringBuffer.append("End Time: "+cursor.getString(6) + "\n\n");
                }
                showData("WestZone Trains", stringBuffer.toString());
            }
        }
        else if(view.getId() == R.id.searchButton){
            Cursor cursor = mydbhelper.searchData(name);
            if (cursor.getCount() == 0){
                showData("Error","Nothing to show");
            }

            else{
                StringBuffer stringBuffer = new StringBuffer();
                while (cursor.moveToNext()){
                    stringBuffer.append("Train no: "+cursor.getString(0) + "\n");
                    stringBuffer.append("Train Name: "+cursor.getString(1) + "\n");
                    stringBuffer.append("Weekly Holiday: "+cursor.getString(2) + "\n");
                    stringBuffer.append("Initial Station: "+cursor.getString(3) + "\n");
                    stringBuffer.append("Start Time: "+cursor.getString(4) + "\n");
                    stringBuffer.append("Destination Station: "+cursor.getString(5) + "\n");
                    stringBuffer.append("End Time: "+cursor.getString(6) + "\n\n");
                }
                showData("Search Result", stringBuffer.toString());
            }
        }
    }

    public void showData(String title, String data){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(data);
        builder.setCancelable(true);
        builder.show();
    }
}
