package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class main_card extends AppCompatActivity{
    private RecyclerView recyclerView;
    private Button btn_card1;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_card);
        recyclerView = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_exit1.setOnClickListener(v -> {
            // Xử lý sự kiện khi người dùng click vào button
            Intent intent = new Intent(main_card.this, main_flashcard.class);
            startActivity(intent);
        });

    }
}
