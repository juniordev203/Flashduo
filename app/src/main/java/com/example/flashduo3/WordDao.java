package com.example.flashduo3;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;


@Dao
public interface WordDao {
    @Insert
    void insert(Word word);

    @Insert
    void insertAll(List<Word> words);

    @Query("Select * FROM Word where id = :wordId")
    Word getById(int wordId);

    @Query("Select id, chinese, meaning, picture FROM Word where id = :wordId")
    Word getWordById(int wordId);

    @Query("Select id, question, options, answer FROM Word where id = :questionId")
    Word getQuestionById(int questionId);

    @Query("SELECT id, sentence FROM Word where id = :sentenceId")
    Word getSentenceById(int sentenceId);

    @Update
    void update(Word word);

    @Query("DELETE FROM word")
    void deleteAll();

}