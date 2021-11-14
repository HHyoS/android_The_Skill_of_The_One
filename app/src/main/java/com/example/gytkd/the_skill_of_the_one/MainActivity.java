package com.example.gytkd.the_skill_of_the_one;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MenuActivity menu;
    MakeActivity make;
    M_ChoiceActivity ch;
    Intent intent1;
    Intent intent2;
    private long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = (MenuActivity) getSupportFragmentManager().findFragmentById(R.id.menuFragment);
        make = new MakeActivity();
        ch = new M_ChoiceActivity();
        intent2 = new Intent(MainActivity.this, PoolgiActivity.class);
        intent1 = new Intent(MainActivity.this, MoonjaeActivity.class);
    }

    public void onFragmentChanged(int index) {
        if (index == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, make).commit();
        else if (index == 1)
            startActivity(intent1);
        else if(index ==2)
            startActivity(intent2);
        else if (index == 3){
            finish();
        android.os.Process.killProcess(android.os.Process.myPid());}
    }

    public interface OnBackPressedListener {
        public void onBack();
    }

    private OnBackPressedListener mBackListener;

    public void setOnBackPressedListener(OnBackPressedListener listener) {
        mBackListener = listener;
    }

    public void onBackPressed() {
        if (mBackListener != null) {
            mBackListener.onBack();
            Log.e("!!!", "Listener is no null");
        } else {
            if (pressedTime == 0) {
                Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                pressedTime = System.currentTimeMillis();
            } else {
                int seconds = (int) (System.currentTimeMillis() - pressedTime);

                if (seconds > 2000) {
                    Toast.makeText(this, "종료하려면 한번 더 누르세요", Toast.LENGTH_SHORT).show();
                    pressedTime = 0;
                } else {
                    super.onBackPressed();
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }

        }
    }
}
