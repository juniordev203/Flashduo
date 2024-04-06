package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class VocabDetailActivity extends AppCompatActivity {
    private ImageView img_exit1;
    private ImageView img_play;
    private card_layout.DataPassListener dataPassListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocab_detail);

        img_exit1 = findViewById(R.id.img_exit1);
        img_play = findViewById(R.id.img_play);
        img_exit1.setOnClickListener(v -> {
            Intent intent = new Intent(VocabDetailActivity.this, main_card.class);
            startActivity(intent);
        });
        img_play.setOnClickListener(v -> {
            dataPassListener.onDataPass("Dữ liệu cần truyền");
        });

        Intent intent = getIntent();
        Vocab vocab = (Vocab) intent.getSerializableExtra("vocab");

        VocabFragment vocabFragment = new VocabFragment(vocab);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, vocabFragment);
        fragmentTransaction.commit();
    }
}
