package com.example.flashduo3;

import android.content.Context;

import com.example.flashduo3.database.AppDatabase;
import com.example.flashduo3.database.AppDatabaseForQuestion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JsonManipulator {
    public void insertJsonDataIntoDatabase(AppDatabase db, AppDatabaseForQuestion dbfq, Context context) {
        new Thread(() -> {
            String json = loadJsonFromAssets(context);
            Type listType = new TypeToken<List<Word>>() {
            }.getType();
            List<Word> words = new Gson().fromJson(json, listType);

            db.wordDao().deleteAll();
            db.wordDao().insertAll(words);

            dbfq.wordDao().deleteAll();
            dbfq.wordDao().insertAll(words);

        }).start();

    }

    private String loadJsonFromAssets(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("words.json");
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
