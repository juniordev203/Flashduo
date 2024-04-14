package com.example.flashduo3.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flashduo3.R;
import com.example.flashduo3.Word;
import com.example.flashduo3.main_card;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MaincardAdapter extends RecyclerView.Adapter<MaincardAdapter.MyViewHolder>{
    private List<Word> words;
    private Context context;
    public MaincardAdapter(Context context, List<Word> words) {
        this.words = words;
    }
    public void setData(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mainvocab, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MaincardAdapter.MyViewHolder holder, int position) {
        Word word = words.get(position);
        if (word == null) {
            return;
        }
        holder.tvChinese.setText(word.chinese);
        holder.tvMeaning.setText(word.meaning);
        holder.tvSentence.setText(word.sentence);
        Glide.with(holder.itemView.getContext())
                .load(word.picture) // Load image from URL
                .into(holder.img_meaning);
    }
    @Override
    public int getItemCount() {
        return words.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChinese;
        private final TextView tvMeaning;
        private TextView tvSentence;
        private ImageView img_meaning;
        private LinearLayout layoutcard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChinese = itemView.findViewById(R.id.tv_chinese_mainvocab);
            tvMeaning = itemView.findViewById(R.id.tv_meaning_mainvocab);
            tvSentence = itemView.findViewById(R.id.tv_sentence_mainvocab);
            img_meaning = itemView.findViewById(R.id.img_meaning);
            layoutcard = itemView.findViewById(R.id.layout_card);
        }
    }
}
