package com.example.flashduo3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.R;
import com.example.flashduo3.Word;
import com.example.flashduo3.main_card;

import java.util.List;

public class MaincardAdapter extends RecyclerView.Adapter<MaincardAdapter.MyViewHolder>{
    private List<Word> words;

    public MaincardAdapter(main_card mainCard, List<Word> words) {
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
            tvChinese = itemView.findViewById(R.id.tv_chinese);
            tvMeaning = itemView.findViewById(R.id.tv_meaning);
        }
    }
}
