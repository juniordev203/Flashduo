package com.example.flashduo3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.adapter.CardAdapter;
import com.example.flashduo3.database.AppDatabase;

import java.util.List;

public class main_flashcard extends AppCompatActivity {
    private ImageView img_exit;
    private Button btn_theghinho;
    private Button btn_tuvungcuaban;
    private Button btn_kiemtra;
    private Button btn_ghepthe;
    private RecyclerView rcv_view;
    private Button btn_card2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);
        initUi();
        new Thread(() -> {
            JsonManipulator jsonmanu = new JsonManipulator();
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            jsonmanu.insertJsonDataIntoDatabase(db, getApplicationContext());
            List<Word> words = db.wordDao().getAll();
            runOnUiThread(() -> {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                CardAdapter cardAdapter = new CardAdapter(words);
                rcv_view.setLayoutManager(linearLayoutManager);
                rcv_view.setAdapter(cardAdapter);
            });
        }).start();

        rcv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Áp dụng animation lật
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.hieu_ung_flashcard);
                set.setTarget(rcv_view);
                set.start();

                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rcv_view.setVisibility(View.GONE);
                        btn_card2.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        btn_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Áp dụng animation lật
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.hieu_ung_flashcard);
                set.setTarget(btn_card2);
                set.start();

                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        rcv_view.setVisibility(View.VISIBLE);
                        btn_card2.setVisibility(View.GONE);
                    }
                });
            }
        });


        ImageView img_exit = findViewById(R.id.img_exit);
        img_exit.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, choosecategory.class);
            startActivity(intent);
        });

        btn_theghinho.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, main_card.class);
            startActivity(intent);
        });
        btn_tuvungcuaban.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, main_vocab.class);
            startActivity(intent);
        });

    }
    private void initUi() {
        img_exit = findViewById(R.id.img_exit);
        btn_theghinho = findViewById(R.id.btn_theghinho);
        btn_tuvungcuaban = findViewById(R.id.btn_tuvungcuaban);
        btn_kiemtra = findViewById(R.id.btn_kiemtra);
        btn_ghepthe = findViewById(R.id.btn_ghepthe);
        btn_card2 = findViewById(R.id.btn_card2);
        rcv_view = findViewById(R.id.rcv_view);
    }


}
