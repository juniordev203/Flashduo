package com.example.flashduo3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Word.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase db;

    public abstract WordDao wordDao();

    public static AppDatabase getDatabase(final Context context){
        if (db == null){
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "word_database").build();
        }
        return db;
    }
}