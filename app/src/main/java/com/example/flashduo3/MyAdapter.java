package com.example.flashduo3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Vocab> data;

    public MyAdapter(Context context, ArrayList<Vocab> data) {
        this.context = context;
        this.data = data;
    }

    // Tạo ViewHolder cho mỗi mục trong danh sách
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        String term = data.get(position).term;
        String def = data.get(position).def;
        holder.termTextView.setText(term);
        holder.defTextView.setText(def);

    }

    // Trả về số lượng mục trong danh sách
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView termTextView;
        private TextView defTextView;
        private TextView ipaTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                        Intent intent = new Intent(context, VocabDetailActivity.class);
                        intent.putExtra("vocab", data.get(position));
                        context.startActivity(intent);
                    }else{
                        VocabFragment vocabFragment = new VocabFragment(data.get(position));
                        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, vocabFragment);
                        fragmentTransaction.commit();
                    }

                }
            });
        }
    }

}


class Vocab implements Serializable {
    String term;
    String def;
    String ipa;
    public Vocab(String term, String def, String ipa) {
        this.term = term;
        this.def = def;
        this.ipa = ipa;
    }
}