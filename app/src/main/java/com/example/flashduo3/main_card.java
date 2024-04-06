package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        recyclerView = findViewById(R.id.recycler_view);

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


        // Khởi tạo Adapter và đặt Adapter cho RecyclerView
        MyAdapter adapter = new MyAdapter(this, listVocab);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);

        img_exit1.setOnClickListener(v -> {
            // Xử lý sự kiện khi người dùng click vào button
            Intent intent = new Intent(main_card.this, main_flashcard.class);
            startActivity(intent);
        });
        img_play.setOnClickListener(v -> {

        });
    }
}
