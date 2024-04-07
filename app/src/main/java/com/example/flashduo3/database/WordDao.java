package com.example.flashduo3.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface WordDao {
    @Insert
    void insertWord(Word word);

    @Insert
    void insertAll(List<Word> words);
    @Query("SELECT * FROM word_table")
    List<Word> getAll();

    @Query("Select * FROM word_table where id = :allId")
    Word getById(int allId);

    @Query("Select id, chinese, meaning FROM word_table where id = :wordId")
    List<Word> getWords(int wordId);
//    @Query("Select id, chinese, meaning, picture FROM Word where id = :wordId")
//    Word getWordById(int wordId);

    @Query("Select id, question, options, answer FROM word_table where id = :questionId")
    Word getQuestionById(int questionId);

    @Query("SELECT id, sentence FROM word_table where id = :sentenceId")
    Word getSentenceById(int sentenceId);

    @Update
    void update(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

}