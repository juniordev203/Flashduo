package com.example.flashduo3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.flashduo3.database.AppDatabase;

import java.util.List;

public class main_vocab extends AppCompatActivity {
    public RecyclerView rcv_vocab;
    private ImageView img_exit1;
    private ImageView img_plus;
    private ImageView img_undo;
    private ImageView img_play;

    private EditText edt_chinese, edt_meaning;
    private ImageView img_add;
    private Button btn_add;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vocab);
        initUi();

        new Thread(() -> {
            JsonManu jsonmanu = new JsonManu();
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            jsonmanu.insertJsonDataIntoDatabase(db, getApplicationContext());
            List<Word> words = db.wordDao().getAll();
            runOnUiThread(() -> {
                MyAdapter myAdapter = new MyAdapter(words);
                rcv_vocab.setLayoutManager(new LinearLayoutManager(this));
                rcv_vocab.setAdapter(myAdapter);
            });
        }).start();




        // Xử lý sự kiện click vào button exit1
        if (img_exit1 != null) {
            img_exit1.setOnClickListener(v -> startFlashcardActivity());
        }

        // Xử lý sự kiện click vào button play
        if (img_play != null) {
            img_play.setOnClickListener(v -> startFlashcardActivity());
        }
    }


    private void initUi() {
        rcv_vocab = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        img_plus = findViewById(R.id.img_plus);
        img_undo = findViewById(R.id.img_undo);
        img_play = findViewById(R.id.img_play);

        edt_chinese = findViewById(R.id.edt_chinese);
        edt_meaning = findViewById(R.id.edt_meaning);
        img_add = findViewById(R.id.img_add);
        btn_add = findViewById(R.id.btn_add);
    }

    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
    private void startFlashcardActivity() {
        Intent intent = new Intent(main_vocab.this, main_flashcard.class);
        startActivity(intent);
    }
}
