package com.example.flashduo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mulitple_choice_result extends AppCompatActivity {

    private ImageView btn_exit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulitple_choice_result);

        // Nhận correctAnswersCount từ Intent
        int correctAnswersCount = getIntent().getIntExtra("CORRECT_ANSWERS_COUNT", 0);

        // Hiển thị correctAnswersCount trong resultTextView
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Số câu trả lời đúng: " + correctAnswersCount + "/10");


        btn_exit = findViewById(R.id.img_exit);

        btn_exit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToChooseCategory();
            }
        }));

    }
    private void backToChooseCategory() {
        // Tạo Intent để chuyển đến màn hình choosecategory
        Intent intent = new Intent(this, choosecategory.class);
        startActivity(intent); // Khởi chạy màn hình mới
        finish(); // Kết thúc màn hình hiện tại để ngăn người dùng quay lại nó bằng nút back
    }

}
