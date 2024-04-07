package com.example.flashduo3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<Word> words;
    private Context context;
    public MyAdapter(main_vocab mainVocab, List<Word> words) {
        this.words = words;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        if (words != null && position < words.size()) {
//            holder.tvChinese.setText(words.get(position).getChinese());
//            holder.tvMeaning.setText(words.get(position).getMeaning());
//        }
        Word word = words.get(position);
        holder.tvChinese.setText(word.getChinese());
        holder.tvMeaning.setText(word.getMeaning());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChinese, tvMeaning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChinese = itemView.findViewById(R.id.tv_chinese);
            tvMeaning = itemView.findViewById(R.id.tv_meaning);
        }
    }
}
