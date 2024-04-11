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
import com.example.flashduo3.database.WordDao;

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
    public abstract WordDao wordDao();

}