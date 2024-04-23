package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.adapter.MaincardAdapter;
import com.example.flashduo3.database.AppDatabase;

import java.util.List;

public class main_card extends AppCompatActivity {
    private RecyclerView rcv_vocab;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_card);

        // Khởi tạo giao diện người dùng
        initUi();

        // Thực hiện các tác vụ trong một luồng mới để tránh chặn luồng giao diện người dùng
        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            List<Word> words = db.wordDao().getAll();
            runOnUiThread(() -> {
                // Tạo linearLayoutManager và adapter cho RecyclerView
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                MaincardAdapter maincardAdapter = new MaincardAdapter(this, words);
                rcv_vocab.setLayoutManager(linearLayoutManager);
                rcv_vocab.setAdapter(maincardAdapter);
            });
        }).start();

        // Thiết lập onClickListener cho nút img_exit1 để chuyển đến activity trước đó
        img_exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_card.this, main_flashcard.class);
                startActivity(intent);
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_card.this, main_vocab.class);
                startActivity(intent);
            }
        });
    }

    // Phương thức này được sử dụng để khởi tạo giao diện người dùng
    private void initUi() {
        rcv_vocab = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);
    }
}
