package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JsonManu jsonmanu = new JsonManu();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        jsonmanu.insertJsonDataIntoDatabase(db, getApplicationContext());
        Button btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });

        new Thread(() -> {
            Word word = db.wordDao().getWordById(2);
            Log.d("debug", word.chinese);
            Log.d("debug", word.meaning);
            Log.d("debug", word.picture);
        }).start();
    }
}
