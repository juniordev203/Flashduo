package com.example.flashduo3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<String> dataList; // Danh sách dữ liệu để hiển thị
    private Context context;

    // Constructor
    public Adapter(Context context, List<String> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // Tạo ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chinese, parent, false);
        return new ViewHolder(view);
    }

    // Gắn dữ liệu vào ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String data = dataList.get(position);
        holder.bind(data);
    }

    // Trả về số lượng item trong danh sách dữ liệu
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // ViewHolder để giữ các thành phần giao diện cho mỗi item
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        // Gắn dữ liệu vào View
        public void bind(String data) {
            // Gắn dữ liệu vào View
        }
    }
}
