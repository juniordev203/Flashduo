package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class main_flashcard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);

        ImageView img_exit = findViewById(R.id.img_exit);
        img_exit.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, chooselanguage.class);
            startActivity(intent);
        });
    }


}
