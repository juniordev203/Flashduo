package com.example.flashduo3;

import static com.example.flashduo3.database.AppDatabase.db;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.adapter.MyAdapter;
import com.example.flashduo3.database.AppDatabase;
import com.google.gson.Gson;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main_vocab extends AppCompatActivity {
    public RecyclerView rcv_vocab;
    private ImageView img_exit1;
    private EditText edt_chinese, edt_meaning;
    private Button btn_add;
    private ImageView img_addimage;
    private MyAdapter myAdapter;
    private List<Word> words;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_vocab);
        initUi();
        initRecyclerView();
        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            words = db.wordDao().getAll();
            runOnUiThread(() -> {
                myAdapter.setData(words);
                myAdapter.notifyDataSetChanged();
            });
        }).start();


        btn_add.setOnClickListener(v -> {
            String strChinese = edt_chinese.getText().toString().trim();
            String strMeaning = edt_meaning.getText().toString().trim();
            if (strChinese.isEmpty() || strMeaning.isEmpty()) {
                return;
            }
            new Thread(() -> {
                Word word = new Word();
                word.chinese = strChinese;
                word.meaning = strMeaning;
                word.picture = "";
                word.answer = "";
                word.question = "";
                word.options = Collections.singletonList("");
                word.id = db.wordDao().getRowCount() + 1;
                db.wordDao().insert(word);
                words.add(word);
                runOnUiThread(() -> {
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Word added", Toast.LENGTH_SHORT).show();
                    edt_chinese.setText("");
                    edt_meaning.setText("");
                    hideSoftKeyboard();
                    updateJsonFile();
                });
                db.wordDao().getAll();
            }).start();
        });

        img_exit1.setOnClickListener(v -> startFlashcardActivity());
    }
    private void updateJsonFile() {
        new Thread(() -> {
            Gson gson = new Gson();
            String json = gson.toJson(words); // Chuyển danh sách từ thành chuỗi JSON
            try {
                FileOutputStream outputStream = openFileOutput("words.json", Context.MODE_PRIVATE);
                outputStream.write(json.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    private void initRecyclerView() {
        // Khởi tạo RecyclerView, adapter và thiết lập layoutManager
        RecyclerView rcv_vocab = findViewById(R.id.rcv_vocab);
        rcv_vocab.setLayoutManager(new LinearLayoutManager(this));
        words = new ArrayList<>(); // Khởi tạo danh sách dữ liệu
        myAdapter = new MyAdapter(words); // Khởi tạo adapter với danh sách dữ liệu
        rcv_vocab.setAdapter(myAdapter); // Gán adapter cho RecyclerView
    }
    private void initUi() {
        rcv_vocab = findViewById(R.id.rcv_vocab);
        img_exit1 = findViewById(R.id.img_exit1);
        edt_chinese = findViewById(R.id.edt_chinese);
        edt_meaning = findViewById(R.id.edt_meaning);
        img_addimage = findViewById(R.id.img_addimage);
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

