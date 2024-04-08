package com.example.flashduo3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Question {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "question")
    public String question;
    @ColumnInfo(name = "options")
    public List<String> options;
    @ColumnInfo(name = "answer")
    public String answer;
    @ColumnInfo(name = "picture")
    public String picture;
}
