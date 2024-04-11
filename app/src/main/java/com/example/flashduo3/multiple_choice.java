package com.example.flashduo3;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.R;
import com.example.flashduo3.database.AppDatabase;
import com.example.flashduo3.database.WordDao;
import com.example.flashduo3.Word;

import java.util.List;


public class multiple_choice extends AppCompatActivity{
    private TextView questionTextView;
    private Button btn_A, btn_B, btn_C, btn_D;
    private WordDao wordDao;
    private List<Word> questions;
    private int currentQuestionIndex = 0;
    private int correctAnswersCount = 0;

    private int currentSelectedButtonId;

    private static final int DELAY_TIME = 1000;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulitple_choice);

        //khoi tao cac thanh phan dao dien can load database
        questionTextView = findViewById(R.id.questionTextView);
        btn_A = findViewById(R.id.btn_A);
        btn_B = findViewById(R.id.btn_B);
        btn_C = findViewById(R.id.btn_C);
        btn_D = findViewById(R.id.btn_D);

        //khoi tao WordDao
        wordDao = AppDatabase.getDatabase(this).wordDao();

        //load cau hoi tu database
        loadQuestion();
        //hien thi cau hoi dau tien
        displayCurrentQuestion();

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
    }

    private void loadQuestion(){
        questions = wordDao.getAll();
    }

    private void displayCurrentQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Word currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.question);
            List<String> options = currentQuestion.options;
            btn_A.setText(options.get(0));
            btn_B.setText(options.get(1));
            btn_C.setText(options.get(2));
            btn_D.setText(options.get(3));
        }
    }

    private void checkAnswer(String selectedAnswer){
        Word currentQuestion = questions.get(currentQuestionIndex);
        String correctAnswer = currentQuestion.answer;
        boolean isCorrect = selectedAnswer.equals(correctAnswer);

        if (isCorrect){
            showCorrectAnswer();
            correctAnswersCount++;
        }else{
            showWrongAnswer(selectedAnswer, correctAnswer);
        }

        currentQuestionIndex++;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayCurrentQuestion();
            }
        }, DELAY_TIME);
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
}

