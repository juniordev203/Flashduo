package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class main_card extends AppCompatActivity{
    private RecyclerView recyclerView;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;
    private ImageView img_flip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_card);
        initUi();
        img_exit1.setOnClickListener(v -> {
            // Xử lý sự kiện khi người dùng click vào button
            Intent intent = new Intent(main_card.this, main_flashcard.class);
            startActivity(intent);
        });
    }
    private void initUi() {
        recyclerView = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);
        img_flip = findViewById(R.id.img_flip);
    }
}
