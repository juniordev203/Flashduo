package com.example.flashduo3.main;

import static android.app.PendingIntent.getActivity;
import static android.icu.util.ULocale.getLanguage;
import static com.example.flashduo3.LocaleHelper.getSelectedLanguage;
import static com.example.flashduo3.LocaleHelper.setLocale;
import static com.example.flashduo3.R.*;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.flashduo3.database.AppDatabaseForQuestion;

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

        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            AppDatabaseForQuestion dbfq = AppDatabaseForQuestion.getDatabase(getApplicationContext());
            if (db.wordDao().getRowCount() == 0) {
                JsonManipulator jsonmanu = new JsonManipulator();
                jsonmanu.insertJsonDataIntoDatabase(db, dbfq, getApplicationContext());
            }
        }).start();
//        JsonManipulator jsonmanu = new JsonManipulator();
//        jsonmanu.insertJsonDataIntoDatabase(db,dbfq, getApplicationContext());

        Button btnbatdau = findViewById(R.id.btnbatdau);
        btnbatdau.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, chooselanguage.class);
            startActivity(intent);
        });

        img_language_vietnam.setOnClickListener(v -> {
            setLocale(this, "vi");
            updateLanguage("vi");
            restartApp();
        });

        img_language_english.setOnClickListener(v -> {
            setLocale(this, "en");
            updateLanguage("en");
            restartApp();
        });

        img_language_chinese.setOnClickListener(v -> {
            setLocale(this, "zh");
            updateLanguage("zh");
            restartApp();
        });

    }
    private void setLocale(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);

        context.createConfigurationContext(configuration);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void updateLanguage(String languageCode) {
        SharedPreferences preferences = getSharedPreferences("language_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language_code", languageCode);
        editor.apply();
    }

    private void restartApp() {
        // Cập nhật ngôn ngữ của ứng dụng
        getLanguage(String.valueOf(this));

        // Khởi động lại activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa tất cả các Activity khác trước đó
        startActivity(intent);
        finish();
    }


}
