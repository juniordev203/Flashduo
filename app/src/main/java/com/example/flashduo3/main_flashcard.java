package com.example.flashduo3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class main_flashcard extends AppCompatActivity {

    private Button btn_theghinho;
    private Button btn_hoc;
    private Button btn_kiemtra;
    private Button btn_ghepthe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);
        Button btn_card1 = findViewById(R.id.btn_card1);
        Button btn_card2 = findViewById(R.id.btn_card2);
        ImageView imageview7 = findViewById(R.id.imageView7);
        ImageView imageview8 = findViewById(R.id.imageView8);
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
                        imageview7.setVisibility(View.GONE);
                        btn_card2.setVisibility(View.VISIBLE);
                        imageview8.setVisibility(View.VISIBLE);
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
                        imageview7.setVisibility(View.VISIBLE);
                        btn_card2.setVisibility(View.GONE);
                        imageview8.setVisibility(View.GONE);
                    }
                });
            }
        });


        ImageView img_exit = findViewById(R.id.img_exit);
        img_exit.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, choosecategory.class);
            startActivity(intent);
        });

        btn_theghinho = findViewById(R.id.btn_theghinho);
        btn_hoc = findViewById(R.id.btn_hoc);
        btn_kiemtra = findViewById(R.id.btn_kiemtra);
        btn_ghepthe = findViewById(R.id.btn_ghepthe);
        btn_theghinho.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, main_card.class);
            startActivity(intent);
        });

    }


}
