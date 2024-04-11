package com.example.flashduo3.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.R;
import com.example.flashduo3.Word;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder>{
    private List<Word> words;
    private Context context;

    public CardAdapter(Context context,List<Word> words) {
        this.context = context;
        this.words = words;
    }
    private void setData(List<Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.MyViewHolder holder, int position) {
        final Word word = words.get(position);
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
        private LinearLayout layoutCardView;
        private final TextView tvChinese;
        private final TextView tvMeaning;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutCardView = itemView.findViewById(R.id.tv_meaning);
            tvChinese = itemView.findViewById(R.id.tv_chinese_mainvocab);
            tvMeaning = itemView.findViewById(R.id.tv_meaning);

            layoutCardView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View dialogView = inflater.inflate(R.layout.alertdialog_detail_card, null);
                TextView defTextView = dialogView.findViewById(R.id.tv_detail_chinese);
                TextView ipaTextView = dialogView.findViewById(R.id.tv_detail_meaning);
                defTextView.setText(words.get(position).meaning);
                ipaTextView.setText(words.get(position).sentence);
                builder.setView(dialogView);
                builder.setCancelable(false);
                builder.setPositiveButton("Đóng", (dialog, which) -> dialog.dismiss());
                builder.setTitle(words.get(position).chinese);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            });
        }
    }
}
