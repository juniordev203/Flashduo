package com.example.flashduo3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.R;
import com.example.flashduo3.database.AppDatabaseForQuestion;
import com.example.flashduo3.database.WordDao;
import com.example.flashduo3.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class multiple_choice extends AppCompatActivity{
    private TextView questionTextView, questionCount;
    private Button btn_A, btn_B, btn_C, btn_D;
    private ImageView btn_next, btn_back, btn_exit;

    private int currentSelectedButtonId = -1;

    private List<Word> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;

//    private static final int DELAY_TIME = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulitple_choice);
        initUI();

        new Thread(() -> {
            AppDatabaseForQuestion db = AppDatabaseForQuestion.getDatabase(getApplicationContext());
            questions.addAll(db.wordDao().getAllQuestion());
            runOnUiThread(() -> displayCurrentQuestion(questions.get(currentQuestionIndex)));
        }).start();

        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelectedButtonId = v.getId();
                checkAnswer(btn_A.getText().toString());
            }
        });

        btn_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelectedButtonId = v.getId();
                checkAnswer(btn_B.getText().toString());
            }
        });

        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelectedButtonId = v.getId();
                checkAnswer(btn_C.getText().toString());
            }
        });

        btn_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelectedButtonId = v.getId();
                checkAnswer(btn_D.getText().toString());
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayCurrentQuestion(questions.get(currentQuestionIndex));
                } else {
                    showResult();
                }
            }

        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayCurrentQuestion(questions.get(currentQuestionIndex));
                } else {
                    backToChooseCategory();
                }
            }
        });

        btn_exit.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToChooseCategory();
            }
        }));

    }

//    private void displayCurrentQuestion(Word question) {
//        resetButtonDrawables(); // Đặt lại drawable của các nút ABCD
//        Type type = new TypeToken<List<String>>() {
//        }.getType();
//        List<String> options = question.getOptions();
//        btn_A.setVisibility(View.VISIBLE);
//        btn_B.setVisibility(View.VISIBLE);
//        btn_C.setVisibility(View.VISIBLE);
//        btn_D.setVisibility(View.VISIBLE);
//
//        questionTextView.setText(question.question);
//        questionCount.setText(question.id + "/10");
//        btn_A.setText(options.get(0));
//        btn_B.setText(options.get(1));
//        btn_C.setText(options.get(2));
//        btn_D.setText(options.get(3));
//    }

    private void displayCurrentQuestion(Word question) {
        resetButtonDrawables(); // Đặt lại drawable của các nút ABCD

        if (question != null && question.getOptions() != null && !question.getOptions().isEmpty()) {
            List<String> options = question.getOptions();

            // Ẩn tất cả các nút
            btn_A.setVisibility(View.VISIBLE);
            btn_B.setVisibility(View.VISIBLE);
            btn_C.setVisibility(View.VISIBLE);
            btn_D.setVisibility(View.VISIBLE);

            // Hiển thị câu hỏi và các lựa chọn
            questionTextView.setText(question.question);
            questionCount.setText(question.id + "/10");
            btn_A.setText(options.get(0));
            btn_B.setText(options.get(1));
            btn_C.setText(options.get(2));
            btn_D.setText(options.get(3));
        } else {
            // Xử lý trường hợp options null hoặc không có dữ liệu
            // Thực hiện các hành động cần thiết, ví dụ như thông báo lỗi
        }
    }


    private void checkAnswer(String selectedAnswer){
        Word currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.getAnswer();
        boolean isCorrect = selectedAnswer.equals(correctAnswer);

        if (isCorrect) {
            showCorrectAnswer();
            correctAnswersCount++;
        } else {
            showWrongAnswer(selectedAnswer, correctAnswer);
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (currentQuestionIndex < questions.size() - 1) {
//                    currentQuestionIndex++;
//                    displayCurrentQuestion(questions.get(currentQuestionIndex));
//                } else {
//                    showResult();
//                }
//            }
//        }, DELAY_TIME);
    }

    private void showCorrectAnswer() {
        Button selectedButton = findViewById(currentSelectedButtonId);
        selectedButton.setBackgroundResource(R.drawable.button_right);
    }

    private void showWrongAnswer(String selectedAnswer, String correctAnswer) {
        Button selectedButton = findViewById(currentSelectedButtonId);
        selectedButton.setBackgroundResource(R.drawable.button_wrong);

        Button correctButton  = null;
        for (Button btn : new Button[]{btn_A, btn_B, btn_C, btn_D}){
            if (btn.getText().toString().equals(correctAnswer)){
                correctButton  = btn;
                break;
            }
        }
        if (correctButton != null){
            correctButton.setBackgroundResource(R.drawable.button_right);
        }
    }

    private void resetButtonDrawables() {
        btn_A.setBackgroundResource(R.drawable.languagebtn); // Đặt lại drawable cho nút A
        btn_B.setBackgroundResource(R.drawable.languagebtn); // Đặt lại drawable cho nút B
        btn_C.setBackgroundResource(R.drawable.languagebtn); // Đặt lại drawable cho nút C
        btn_D.setBackgroundResource(R.drawable.languagebtn); // Đặt lại drawable cho nút D
    }

    private void backToChooseCategory() {
        // Tạo Intent để chuyển đến màn hình choosecategory
        Intent intent = new Intent(this, choosecategory.class);
        startActivity(intent); // Khởi chạy màn hình mới
        finish(); // Kết thúc màn hình hiện tại để ngăn người dùng quay lại nó bằng nút back
    }

    private void showResult(){
        Intent intent = new Intent(this, mulitple_choice_result.class);
        intent.putExtra("CORRECT_ANSWERS_COUNT", correctAnswersCount);
        startActivity(intent); // Khởi chạy màn hình mới
        finish(); // Kết thúc màn hình hiện tại để ngăn người dùng quay lại nó bằng nút back
    }

    private void initUI(){
        //khoi tao cac thanh phan dao dien can load database
        questionTextView = findViewById(R.id.questionTextView);
        questionCount = findViewById(R.id.questionCount);
        btn_A = findViewById(R.id.btn_A);
        btn_B = findViewById(R.id.btn_B);
        btn_C = findViewById(R.id.btn_C);
        btn_D = findViewById(R.id.btn_D);
        btn_next = findViewById(R.id.img_play);
        btn_back = findViewById(R.id.img_undo);
        btn_exit = findViewById(R.id.img_exit);

    }
}

