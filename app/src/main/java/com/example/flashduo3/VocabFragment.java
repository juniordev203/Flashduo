package com.example.flashduo3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VocabFragment  extends Fragment {


    Vocab vocab;
    VocabFragment(Vocab vocab){
        this.vocab = vocab;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_layout, container, false);
        TextView defTextView = rootView.findViewById(R.id.termTextView);
        defTextView.setText(vocab.def);
        TextView ipaTextView = rootView.findViewById(R.id.defTextView);
        ipaTextView.setText(vocab.ipa);
        return rootView;
    }
}


