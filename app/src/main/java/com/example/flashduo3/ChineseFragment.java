package com.example.flashduo3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.PrimitiveIterator;

public class ChineseFragment extends Fragment {



    public ChineseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChineseFragment newInstance() {
        return new ChineseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chinese, container, false);
        // Inflate the layout for this fragment
        return rootView;

    }
}