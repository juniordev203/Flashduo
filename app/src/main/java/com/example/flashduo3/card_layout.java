package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class card_layout extends AppCompatActivity {
    private ImageView img_exit1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_layout);
        img_exit1 = findViewById(R.id.img_exit1);
        img_exit1.setOnClickListener(v -> {
            Intent intent = new Intent(card_layout.this, main_card.class);
            startActivity(intent);
        });
    }
    public interface DataPassListener {
        void onDataPass(String data); // Đây là phương thức để truyền dữ liệu
    }
    public void onDataPass(String data) {
        // Xử lý dữ liệu ở đây, ví dụ: chuyển dữ liệu vào Fragment khác

    }

}