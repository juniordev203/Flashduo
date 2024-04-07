package com.example.flashduo3.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.flashduo3.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM word")
    List<Word> getListWord();
}
