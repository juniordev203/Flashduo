package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class choosecategory extends AppCompatActivity {
    boolean optionSelected = false;
    private Button btn_tracnghiem;
    private Button btn_flashcard;
    private Button btn_maucau;
    private Button btn_phatam;
    private Button btn_game;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosecategory);

        btn_tracnghiem = findViewById(R.id.btn_tracnghiem);
        btn_flashcard = findViewById(R.id.btn_flashcard);
        btn_maucau = findViewById(R.id.btn_maucau);
        btn_phatam = findViewById(R.id.btn_phatam);
        btn_game = findViewById(R.id.btn_game);
        Button btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(v -> {
            if (optionSelected) {
                Intent intent = new Intent(choosecategory.this, main_flashcard.class);
                startActivity(intent);
            } else {
                Toast.makeText(choosecategory.this, "Vui lòng chọn một tùy chọn", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView img_exit2 = findViewById(R.id.img_exit1);
        img_exit2.setOnClickListener(v -> {
            Intent intent = new Intent(choosecategory.this, chooselanguage.class);
            startActivity(intent);
        });
        btn_tracnghiem.setOnClickListener(v -> selectOption(btn_tracnghiem));
        btn_flashcard.setOnClickListener(v -> selectOption(btn_flashcard));
        btn_maucau.setOnClickListener(v -> selectOption(btn_maucau));
        btn_phatam.setOnClickListener(v -> selectOption(btn_phatam));
        btn_game.setOnClickListener(v -> selectOption(btn_game));


    }
    private void selectOption(Button selectedButton) {
        btn_tracnghiem.setBackgroundResource(R.drawable.button_default_choose);
        btn_flashcard.setBackgroundResource(R.drawable.button_default_choose);
        btn_maucau.setBackgroundResource(R.drawable.button_default_choose);
        btn_phatam.setBackgroundResource(R.drawable.button_default_choose);
        btn_game.setBackgroundResource(R.drawable.button_default_choose);
        selectedButton.setBackgroundResource(R.drawable.button_hover_choose);
        optionSelected = true;
    }
}
