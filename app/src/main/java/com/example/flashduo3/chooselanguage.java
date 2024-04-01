package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class chooselanguage extends AppCompatActivity {
    private boolean languageSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooselanguage);

        Button btn_continue = findViewById(R.id.btn_continue);
        Button btn_chinese = findViewById(R.id.btn_chinese);
        Button btn_english = findViewById(R.id.btn_english);
        Button btn_french = findViewById(R.id.btn_french);
        Button btn_russian = findViewById(R.id.btn_russian);
        Button btn_spain = findViewById(R.id.btn_spain);

        btn_continue.setOnClickListener(v -> {
            if (languageSelected) {
                Intent intent = new Intent(chooselanguage.this, subject.class);
                startActivity(intent);
            } else {
                Toast.makeText(chooselanguage.this, "Vui lòng chọn ngôn ngữ của bạn", Toast.LENGTH_SHORT).show();
            }
        });
        btn_chinese.setOnClickListener(v -> {
            languageSelected = true;
            btn_chinese.setBackgroundResource(R.drawable.button_hover_choose);
        });
        btn_english.setOnClickListener(v -> {
            languageSelected = true;
            btn_english.setBackgroundResource(R.drawable.button_hover_choose);
        });
        btn_french.setOnClickListener(v -> {
            languageSelected = true;
            btn_french.setBackgroundResource(R.drawable.button_hover_choose);
        });
        btn_russian.setOnClickListener(v -> {
            languageSelected = true;
            btn_russian.setBackgroundResource(R.drawable.button_hover_choose);
        });
        btn_spain.setOnClickListener(v -> {
            languageSelected = true;
            btn_spain.setBackgroundResource(R.drawable.button_hover_choose);
        });
        ImageView img_exit1 = findViewById(R.id.img_exit1);
        img_exit1.setOnClickListener(v -> {
            Intent intent = new Intent(chooselanguage.this, MainActivity.class);
            startActivity(intent);
        });
    }
}

