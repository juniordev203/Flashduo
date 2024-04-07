package com.example.flashduo3.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.JsonManu;
import com.example.flashduo3.R;
import com.example.flashduo3.chooselanguage;
import com.example.flashduo3.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JsonManu jsonManu = new JsonManu();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        jsonManu.insertJsonDataIntoDatabase(db, getApplicationContext());
        Button btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });
    }
}