package com.example.flashduo3.adapter;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashduo3.R;
import com.example.flashduo3.Word;
import com.example.flashduo3.main_card;

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

        holder.layoutcard.setOnClickListener(new View.OnClickListener() {
            public ImageView tv_chinese_mainvocab;
            private TextView tv_meaning_mainvocab;
            private TextView tv_sentence_mainvocab;
            @Override
            public void onClick(View v) {
                onClickAnimation(holder);
            }
        });
    }

    private void onClickAnimation(MaincardAdapter.MyViewHolder holder) {
        LinearLayout layout_card = holder.layoutcard;
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context.getApplicationContext(), R.animator.hieu_ung_flashcard);
        set.setTarget(layout_card);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            TextView tv_chinese = holder.tvChinese;
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tv_chinese.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChinese;
        private final TextView tvMeaning;
        private LinearLayout layoutcard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChinese = itemView.findViewById(R.id.tv_chinese_mainvocab);
            tvMeaning = itemView.findViewById(R.id.tv_meaning);
            layoutcard = itemView.findViewById(R.id.layout_card);
        }
    }
}
