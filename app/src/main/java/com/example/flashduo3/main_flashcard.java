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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class main_flashcard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);
        Button btn_card1 = findViewById(R.id.btn_card1);
        Button btn_card2 = findViewById(R.id.btn_card2);

        btn_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Áp dụng animation lật
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.hieu_ung_flashcard);
                set.setTarget(btn_card1);
                set.start();

                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        btn_card1.setVisibility(View.GONE);
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
                        btn_card1.setVisibility(View.VISIBLE);
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

        Button btn_theghinho = findViewById(R.id.btn_theghinho);
        Button btn_tuvungcuaban = findViewById(R.id.btn_tuvungcuaban);
        Button btn_kiemtra = findViewById(R.id.btn_kiemtra);
        Button btn_ghepthe = findViewById(R.id.btn_ghepthe);
        btn_theghinho.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, main_card.class);
            startActivity(intent);
        });
        btn_tuvungcuaban.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, main_vocab.class);
            startActivity(intent);
        });

    }


}
