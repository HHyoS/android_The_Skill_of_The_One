package com.example.gytkd.the_skill_of_the_one;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListItemView extends LinearLayout {
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    public ListItemView(Context context){
        super(context);
        init(context);
    }
    public ListItemView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
    }
    public void setName(String name){
        textView.setText(name);
    }
    public void setBoon(String boon){
        textView1.setText(boon);
    }
    public void setQ(String Q){
        textView2.setText(Q);
    }
    public void setK(String K){
        textView3.setText(K);
    }
}
