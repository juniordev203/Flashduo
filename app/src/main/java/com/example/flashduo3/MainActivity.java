package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        Button btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(v -> {
            insertJsonDataIntoDatabase(db);
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });
    }

    private void insertJsonDataIntoDatabase(AppDatabase db) {
        new Thread(() -> {
            String json = loadJsonFromAssets();

            Type listType = new TypeToken<List<Word>>() {
            }.getType();
            List<Word> words = new Gson().fromJson(json, listType);

            db.wordDao().deleteAll();
            db.wordDao().insertAll(words);
        }).start();

    }

    private String loadJsonFromAssets() {
        String json;
        try {
            InputStream is = getAssets().open("words.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
