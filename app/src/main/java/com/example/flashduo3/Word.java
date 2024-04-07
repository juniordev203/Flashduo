package com.example.flashduo3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "chinese")
    public String chinese;

    @ColumnInfo(name = "meaning")
    public String meaning;

    @ColumnInfo(name = "sentence")
    public String sentence;

    public String getChinese() {
        return chinese;
    }

    public String getMeaning() {
        return meaning;
    }
}
