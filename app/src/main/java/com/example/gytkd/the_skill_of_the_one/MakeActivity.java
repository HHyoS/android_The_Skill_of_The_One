package com.example.gytkd.the_skill_of_the_one;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MakeActivity extends Fragment implements MainActivity.OnBackPressedListener{
    MenuActivity menu;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.makemain, container, false);
        Button b1 = (Button) rootView.findViewById(R.id.ch_button);
       Button b2 = (Button) rootView.findViewById(R.id.short_button);
       menu = new MenuActivity();
      b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), M_ChoiceActivity.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), M_ShortActivity.class);
            startActivity(intent);
        }
    });
        return rootView;
    }
    public void onBack(){
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnBackPressedListener(null);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, menu).commit();
    }
    public void onAttach(Context context){
        super.onAttach(context);
        ((MainActivity)context).setOnBackPressedListener(this);
    }

}
