package com.example.flashduo3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class main_flashcard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);

        ConstraintLayout constraintLayout7 = findViewById(R.id.constraintLayout7);
        ConstraintLayout constraintLayout8 = findViewById(R.id.constraintLayout8); // Đây là ConstraintLayout mới bạn muốn hiển thị sau khi lật
        ImageView imageview15 = findViewById(R.id.imageView15);
        ImageView imageview21 = findViewById(R.id.imageView21);
        imageview15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Áp dụng animation lật
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.hieu_ung_flashcard);
                set.setTarget(constraintLayout7);
                set.start();

                // Sau khi animation hoàn thành, hiển thị ConstraintLayout mới
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        constraintLayout7.setVisibility(View.GONE);
                        constraintLayout8.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        imageview21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Áp dụng animation lật
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.hieu_ung_flashcard);
                set.setTarget(constraintLayout8);
                set.start();

                // Sau khi animation hoàn thành, hiển thị ConstraintLayout mới
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        constraintLayout7.setVisibility(View.VISIBLE);
                        constraintLayout8.setVisibility(View.GONE);
                    }
                });
            }
        });


        ImageView img_exit = findViewById(R.id.img_exit);
        img_exit.setOnClickListener(v -> {
            Intent intent = new Intent(main_flashcard.this, choosecategory.class);
            startActivity(intent);
        });


    }


}
