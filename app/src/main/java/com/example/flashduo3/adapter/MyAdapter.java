package com.example.flashduo3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.R;
import com.example.flashduo3.Word;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<Word> words;

    public MyAdapter(List<Word> words) {
        this.words = words;
    }
    public void setData(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Word word = words.get(position);
        if (word == null) {
            return;
        }
        holder.tvChinese.setText(word.chinese);
        holder.tvMeaning.setText(word.meaning);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChinese;
        private final TextView tvMeaning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChinese = itemView.findViewById(R.id.tv_chinese_mainvocab);
            tvMeaning = itemView.findViewById(R.id.tv_meaning);
        }
    }
}
