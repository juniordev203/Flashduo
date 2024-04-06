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

import java.util.ArrayList;

public class main_flashcard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_flashcard);
        ArrayList<Vocab> listVocab = new ArrayList<>();
        listVocab.add(new Vocab("猫", "Mèo", "[māo]")); // Mèo (chó mèo) - Mao
        listVocab.add(new Vocab("老虎", "Hổ", "[lǎo hǔ]")); // Hổ - Lão hổ
        listVocab.add(new Vocab("鱼", "Cá", "[yú]")); // Cá - Ngư
        listVocab.add(new Vocab("鸟", "Chim", "[niǎo]")); // Chim - Điểu
        listVocab.add(new Vocab("大象", "Voi", "[dà xiàng]")); // Voi - Đại tượng
        listVocab.add(new Vocab("蛇", "Rắn", "[shé]")); // Rắn - Xà
        listVocab.add(new Vocab("猴子", "Khỉ", "[hóu zi]")); // Khỉ - Hầu tử
        listVocab.add(new Vocab("熊", "Gấu", "[xióng]")); // Gấu - Hùng
        listVocab.add(new Vocab("牛", "Bò", "[niú]")); // Bò - Ngưu
        listVocab.add(new Vocab("马", "Ngựa", "[mǎ]")); // Ngựa - Mã
        listVocab.add(new Vocab("鸭子", "Vịt", "[yā zi]")); // Vịt - Ác tử
        listVocab.add(new Vocab("鸡", "Gà", "[jī]")); // Gà - Kê
        listVocab.add(new Vocab("羊", "Cừu", "[yáng]")); // Cừu - Dương
        listVocab.add(new Vocab("山羊", "Dê", "[shān yáng]")); // Dê - Sơn dương
        listVocab.add(new Vocab("狼", "Sói", "[láng]")); // Sói - Lang
        listVocab.add(new Vocab("狐狸", "Cáo", "[hú lí]")); // Cáo - Hồ li
        listVocab.add(new Vocab("兔子", "Thỏ", "[tù zi]")); // Thỏ - Thố tử
        listVocab.add(new Vocab("青蛙", "Ếch", "[qīng wā]")); // Ếch - Thanh oa
        listVocab.add(new Vocab("蚂蚁", "Kiến", "[mǎ yǐ]")); // Kiến - Ma dĩ
        listVocab.add(new Vocab("蜜蜂", "Ong", "[mì fēng]")); // Ong - Mật phong


        Button btn_card1 = findViewById(R.id.btn_card1);
        Button btn_card2 = findViewById(R.id.btn_card2);
        Button btn_card3 = findViewById(R.id.btn_card3);

        btn_card1.setText(listVocab.get(0).term);
        btn_card2.setText(listVocab.get(0).def);
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
