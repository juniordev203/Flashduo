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
public abstract class AppDatabaseForQuestion extends RoomDatabase {
    public static final String DATABASE_NAME = "questions_database";
    public static AppDatabaseForQuestion db;
    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word_table ADD COLUMN picture TEXT");
        }
    };
    public static AppDatabaseForQuestion getDatabase(final Context context) {
        if (db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabaseForQuestion.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
    public abstract WordDao wordDao();

}