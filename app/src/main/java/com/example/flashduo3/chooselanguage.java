package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.main.MainActivity;


public class chooselanguage extends AppCompatActivity {
    // Khai báo biến languageSelected ở đây để nó có thể được truy cập từ mọi phương thức trong lớp
    boolean languageSelected = false;
    private Button btn_chinese;
    private Button btn_english;
    private Button btn_french;
    private Button btn_russian;
    private Button btn_spain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooselanguage);

        btn_chinese = findViewById(R.id.btn_chinese);
        btn_english = findViewById(R.id.btn_english);
        btn_french = findViewById(R.id.btn_french);
        btn_russian = findViewById(R.id.btn_russian);
        btn_spain = findViewById(R.id.btn_spain);
        Button btn_continue = findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(v -> {
            if (languageSelected) {
                Intent intent = new Intent(chooselanguage.this, choosecategory.class);
                startActivity(intent);
            } else {
                Toast.makeText(chooselanguage.this, "Vui lòng chọn ngôn ngữ của bạn", Toast.LENGTH_SHORT).show();
            }
        });

        // Gọi phương thức selectLanguage khi click vào các nút ngôn ngữ
        btn_chinese.setOnClickListener(v -> selectLanguage(btn_chinese));
        btn_english.setOnClickListener(v -> selectLanguage(btn_english));
        btn_french.setOnClickListener(v -> selectLanguage(btn_french));
        btn_russian.setOnClickListener(v -> selectLanguage(btn_russian));
        btn_spain.setOnClickListener(v -> selectLanguage(btn_spain));

        ImageView img_exit1 = findViewById(R.id.img_exit1);
        img_exit1.setOnClickListener(v -> {
            Intent intent = new Intent(chooselanguage.this, MainActivity.class);
            startActivity(intent);
        });
    }

    // Phương thức selectLanguage được di chuyển ra khỏi onCreate
    private void selectLanguage(Button selectedButton) {
        // Bỏ chọn tất cả các ngôn ngữ khác
        // Đổi tên biến btn_chinese thành selectedButton để sử dụng biến được truyền vào
        btn_chinese.setBackgroundResource(R.drawable.button_default_choose);
        btn_english.setBackgroundResource(R.drawable.button_default_choose);
        btn_french.setBackgroundResource(R.drawable.button_default_choose);
        btn_russian.setBackgroundResource(R.drawable.button_default_choose);
        btn_spain.setBackgroundResource(R.drawable.button_default_choose);

        // Chọn ngôn ngữ được click và cập nhật biến languageSelected
        selectedButton.setBackgroundResource(R.drawable.button_hover_choose);
        languageSelected = true;
    }
}

