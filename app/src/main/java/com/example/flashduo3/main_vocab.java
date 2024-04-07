package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.database.AppDatabase;

public class main_vocab extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vocab);
        initUi();

        // Kiểm tra nullability trước khi sử dụng đối tượng
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

            int wordId = 0;
            MyAdapter adapter = new MyAdapter(this, db.wordDao().getWords(wordId));
            recyclerView.setAdapter(adapter);
        }

        // Xử lý sự kiện click vào button exit1
        if (img_exit1 != null) {
            img_exit1.setOnClickListener(v -> startFlashcardActivity());
        }

        // Xử lý sự kiện click vào button play
        if (img_play != null) {
            img_play.setOnClickListener(v -> startFlashcardActivity());
        }
    }

    private void initUi() {
        recyclerView = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);
    }

    private void startFlashcardActivity() {
        Intent intent = new Intent(main_vocab.this, main_flashcard.class);
        startActivity(intent);
    }
}
