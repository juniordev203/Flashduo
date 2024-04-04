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

    private ImageView img_exit1, img_plus, img_undo, img_play;
    private Button btn_card1;

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
        img_exit1 = rootView.findViewById(R.id.img_exit1);
        img_plus = rootView.findViewById(R.id.img_plus);
        img_undo = rootView.findViewById(R.id.img_undo);
        img_play = rootView.findViewById(R.id.img_play);
        btn_card1 = rootView.findViewById(R.id.btn_card1);

        // Inflate the layout for this fragment
        return rootView;

    }
}