package com.example.flashduo3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.flashduo3.Converters;
import com.example.flashduo3.Word;

@Database(entities = {Word.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word_table ADD COLUMN picture TEXT");
        }
    };
    public static AppDatabase db;

    public abstract WordDao wordDao();

    public static synchronized AppDatabase getDatabase(Context context){
        if (db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}