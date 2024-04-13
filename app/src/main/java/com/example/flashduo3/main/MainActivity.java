package com.example.flashduo3.main;

import static com.example.flashduo3.LocaleHelper.getSelectedLanguage;
import static com.example.flashduo3.R.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashduo3.JsonManipulator;
import com.example.flashduo3.LocaleHelper;
import com.example.flashduo3.R;
import com.example.flashduo3.chooselanguage;
import com.example.flashduo3.database.AppDatabase;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ImageView img_language_vietnam;
    private ImageView img_language_english;
    private ImageView img_language_chinese;
    private TextView textView2;
    private Button btnbatdau;

    private Context context;
    private Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img_language_vietnam = findViewById(R.id.img_language_vietnam);
        img_language_english = findViewById(R.id.img_language_english);
        img_language_chinese = findViewById(R.id.img_language_chinese);
        btnbatdau = findViewById(R.id.btnbatdau);
        textView2 = findViewById(R.id.textView2);

//        String language = getSelectedLanguage(this);
//        LocaleHelper.setLocale(this, language);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
//        JsonManipulator jsonmanu = new JsonManipulator();
//        jsonmanu.insertJsonDataIntoDatabase(db, getApplicationContext());
        btnbatdau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });

//        img_language_vietnam.setOnClickListener(v -> {
//            context = LocaleHelper.setLocale(MainActivity.this, "vi");
//            resources = context.getResources();
//            textView2.setText(resources.getString(R.string.h_c_ng_n_ng_m_i_c_ng_flashduo));
//            btnbatdau.setText(resources.getString(R.string.text_batdau));
//        });
//        img_language_english.setOnClickListener(v -> {
//            context = LocaleHelper.setLocale(MainActivity.this, "en");
//            resources = context.getResources();
//            textView2.setText(resources.getString(R.string.h_c_ng_n_ng_m_i_c_ng_flashduo));
//            btnbatdau.setText(resources.getString(R.string.text_batdau));
//
//        });
//        img_language_chinese.setOnClickListener(v -> {
//            setLanguage("zh");
//            recreate();
//        });

        //languageApp
    }
//    private void setLanguage(String language) {
//        Resources res = getResources();
//        Configuration conf = res.getConfiguration();
//        Locale locale = new Locale(language);
//        conf.setLocale(locale);
//        res.updateConfiguration(conf, res.getDisplayMetrics());
//    }
}