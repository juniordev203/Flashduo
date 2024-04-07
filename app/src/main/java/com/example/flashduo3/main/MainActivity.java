package com.example.flashduo3.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.JsonManu;
import com.example.flashduo3.R;
import com.example.flashduo3.Word;
import com.example.flashduo3.chooselanguage;
import com.example.flashduo3.database.AppDatabase;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonManu jsonmanu = new JsonManu();
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        jsonmanu.insertJsonDataIntoDatabase(db, getApplicationContext());
        Button btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });
        new Thread(() -> {
            Word word = db.wordDao().getWordById(1);
            Log.d("debug", word.chinese);
            Log.d("debug", word.meaning);
            Log.d("debug", word.picture);

        }).start();
    }
}