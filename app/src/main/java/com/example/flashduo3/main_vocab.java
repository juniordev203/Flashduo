package com.example.flashduo3;

import static android.content.ContentValues.TAG;
import static com.example.flashduo3.database.AppDatabase.db;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.adapter.MyAdapter;
import com.example.flashduo3.database.AppDatabase;

import com.google.gson.Gson;

import com.example.flashduo3.database.WordDao;



import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main_vocab extends AppCompatActivity {
    public static final String TAG = "main_vocab";
    private static final int MY_REQUEST_CODE = 1;
    private String selectedImagePath;
    public RecyclerView rcv_vocab;
    private ImageView img_exit1;
    private EditText edt_chinese, edt_meaning;
    private Button btn_add;
    private ImageView img_addimage;
    private MyAdapter myAdapter;
    private List<Word> words;
    private Uri mUri;

    // Khai báo và định nghĩa ActivityResultLauncher để xử lý kết quả của việc chọn hình ảnh
    private final ActivityResultLauncher  <Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult: ");
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            img_addimage.setImageBitmap(bitmap);
                            selectedImagePath = getPathFromUri(uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // Lấy đường dẫn từ Uri của hình ảnh
                private String getPathFromUri(Uri uri) {
                    String path = null;
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    if (cursor != null) {
                        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        path = cursor.getString(columnIndex);
                        cursor.close();
                    }
                    return path;
                }
            });


    @Override
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
        img_addimage.setOnClickListener(v -> {
            onClickRequestPermission();
        });

        btn_add.setOnClickListener(v -> {
            String strChinese = edt_chinese.getText().toString().trim();
            String strMeaning = edt_meaning.getText().toString().trim();
            String strPicture = selectedImagePath;
            if (strChinese.isEmpty() || strMeaning.isEmpty() || strPicture.isEmpty()) {
                return;
            }
            //Thêm từ mới vào cơ sở dữ liệu và cập nhật RecyclerView
            new Thread(() -> {
                Word word = new Word();
                word.chinese = strChinese;
                word.meaning = strMeaning;
                word.picture = strPicture;
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
    // Yêu cầu quyền truy cập vào thư viện hình ảnh
    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }else {
            String [] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, MY_REQUEST_CODE);
        }
    }
    // Phương thức này được gọi sau khi người dùng đã cấp quyền truy cập vào thư viện hình ảnh
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
    // Mở thư viện hình ảnh
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
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

