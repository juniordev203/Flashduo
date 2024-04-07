package com.example.flashduo3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "word")
public class Word implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordchinese;
    private String wordvietnamese;
}
