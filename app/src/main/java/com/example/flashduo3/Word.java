package com.example.flashduo3;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Word implements java.io.Serializable{
    @PrimaryKey
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


    public List<String> getOptions() {
        return options;
    }

    public String getAnswer(){
        return answer;
    }

//    public Word(String strChinese, String strMeaning) {
//        this.chinese = strChinese;
//        this.meaning = strMeaning;
//    }
}
