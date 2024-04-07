package com.example.flashduo3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "chinese")
    public String chinese;

    @ColumnInfo(name = "meaning")
    public String meaning;

    @ColumnInfo(name = "sentence")
    public String sentence;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "options")
    public List<String> options;

    @ColumnInfo(name = "answer")
    public String answer;

    @ColumnInfo(name = "picture")
    public String picture;

}