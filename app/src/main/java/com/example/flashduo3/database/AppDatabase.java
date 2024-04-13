package com.example.flashduo3.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.flashduo3.Converters;
import com.example.flashduo3.Word;
import com.example.flashduo3.database.WordDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Word.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "word_database";
    public static AppDatabase db;
    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word_table ADD COLUMN picture TEXT");
        }
    };
    public static synchronized AppDatabase getDatabase(Context context){
        if (db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }



    public static List<Word> loadQuestionsFromAsset(Context context) {
        try {
            InputStream is = context.getAssets().open("words.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Word>>() {
            }.getType();
            return gson.fromJson(json, listType);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public abstract WordDao wordDao();

}