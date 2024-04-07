//package com.example.flashduo3.database;
//
//import android.content.Context;
//
//import androidx.room.Dao;
//import androidx.room.Database;
//import androidx.room.Query;
//import androidx.room.Room;
//
//import com.example.flashduo3.Word;
//
//@Database(entities = {Word.class}, version = 1)
//public class WordDatabase {
//    private static final String DATABASE_NAME = "word.db";
//    private static WordDatabase instance;
//
//    public static synchronized WordDatabase getInstance(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, DATABASE_NAME)
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return instance;
//    }
//    public abstract WordDao wordDao();
//
//}
