package com.example.flashduo3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VietnameseFragment extends Fragment {
    public VietnameseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static VietnameseFragment newInstance(String param1, String param2) {
        VietnameseFragment fragment = new VietnameseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vietnamese, container, false);
    }
}