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

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}