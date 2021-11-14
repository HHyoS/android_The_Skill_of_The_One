package com.example.gytkd.the_skill_of_the_one;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuActivity extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_menu, container, false);
        Button b1 = (Button) rootView.findViewById(R.id.makebutton);
        Button b2 = (Button) rootView.findViewById(R.id.funcbutton);
        Button b3 = (Button) rootView.findViewById(R.id.exambutton);
        Button b4 = (Button) rootView.findViewById(R.id.exitbutton);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity mk = (MainActivity) getActivity();
                mk.onFragmentChanged(0);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mk = (MainActivity) getActivity();
                mk.onFragmentChanged(1);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mk = (MainActivity) getActivity();
                mk.onFragmentChanged(2);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mk = (MainActivity) getActivity();
                mk.onFragmentChanged(3);
            }
        });
        return rootView;
    }
}

