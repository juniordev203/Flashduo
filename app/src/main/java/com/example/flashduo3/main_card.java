package com.example.flashduo3;

import android.animation.AnimatorSet;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.adapter.CardAdapter;
//import com.example.flashduo3.adapter.MaincardAdapter;
import com.example.flashduo3.adapter.MaincardAdapter;
import com.example.flashduo3.database.AppDatabase;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.List;
import java.util.Objects;

public class main_card extends AppCompatActivity{
    private RecyclerView rcv_vocab;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;
    private ImageView img_flip;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_card);

        initUi();

        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            List<Word> words = db.wordDao().getAll();
            runOnUiThread(() -> {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                MaincardAdapter maincardAdapter = new MaincardAdapter(this,words);
                rcv_vocab.setLayoutManager(linearLayoutManager);
                rcv_vocab.setAdapter(maincardAdapter);

            });
        }).start();

        img_exit1.setOnClickListener(v -> {
            Intent intent = new Intent(main_card.this, main_flashcard.class);
            startActivity(intent);
        });
        img_plus.setOnClickListener(v -> {
            Intent intent = new Intent(main_card.this, main_vocab.class);
            startActivity(intent);
        });

        img_flip.setOnClickListener(v -> {
            Intent intent = new Intent(main_card.this, main_flashcard.class);
            startActivity(intent);
        });
    }
    private void initUi() {
        rcv_vocab = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);
        img_flip = findViewById(R.id.img_flip);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == ImagePicker.REQUEST_CODE && resultCode == RESULT_OK) {
//            Uri uri = data.getData();
//        }
//    }

}
