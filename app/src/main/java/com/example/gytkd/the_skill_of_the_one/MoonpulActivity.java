package com.example.gytkd.the_skill_of_the_one;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MoonpulActivity extends AppCompatActivity {

    private ImageView imav;
    private Uri imgUri, photoURI, albumURI;

    private String mCurrentPhotoPath;

    private static final int FROM_CAMERA = 0;

    private static final int FROM_ALBUM = 1;
    Uri phoho;
    TextView edt;
    TextView Question;
    TextView example;
    EditText answer;
    TextView answ1;
    TextView answ2;
    TextView answ3;
    TextView answ4;
    TextView answ5;
    String qtitle;  //
    String ans_choi; // 답
    String boonroo;
    String Nname;
    Bitmap bm1;
    Bitmap bm2;
    String moonjae;
    int juge;


    String example1;
    String example2;
    String example3;
    String example4;
    String example5;
    String photous;
    String moonjaewrite;
    //문제보기 5개
    SaveActivity.Dbhelper dbhelper;
    SQLiteDatabase db;

    int asave = 3;
    int frameindex = 0;
    Integer integer1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moonpul);
        Intent intent = getIntent();
        final int permissionCheck0 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        final int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        final int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);


        dbhelper = new SaveActivity.Dbhelper(this);
        db = dbhelper.getWritableDatabase();
        answ1 = (TextView) findViewById(R.id.answ1);
        answ2 = (TextView) findViewById(R.id.answ2);
        answ3 = (TextView) findViewById(R.id.answ3);
        answ4 = (TextView) findViewById(R.id.answ4);
        answ5 = (TextView) findViewById(R.id.answ5);
        Question = (TextView) findViewById(R.id.questionET);
        answer = (EditText) findViewById(R.id.answer);
        imav = (ImageView) findViewById(R.id.imageV);
        edt = (TextView) findViewById(R.id.write);
        Button saveBt = (Button) findViewById(R.id.saveBt);
        boonroo = intent.getStringExtra("분류");
        Nname = intent.getStringExtra("문제이름");
        integer1 = intent.getIntExtra("id", 0);

        Cursor c = db.rawQuery("select * from choice where _id=" + integer1 + "", null);
        if(c!=null && c.moveToFirst()) {
            if (c.getString(11).equals("nope") && !c.getString(12).equals("nope")) {
                edt.setVisibility(View.INVISIBLE);
                imav.setVisibility(View.VISIBLE);
                Uri photouu = Uri.parse(c.getString(12));
                try {
                    bm1 = MediaStore.Images.Media.getBitmap(getContentResolver(), photouu);
                    imav.setImageBitmap(bm1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (!c.getString(11).equals("nope") && c.getString(12).equals("nope")) {
                edt.setVisibility(View.VISIBLE);
                imav.setVisibility(View.INVISIBLE);
                edt.setText(c.getString(11));
            }
            Question.setText(c.getString(4));
            answ1.setText(c.getString(5));
            answ2.setText(c.getString(6));
            answ3.setText(c.getString(7));
            answ4.setText(c.getString(8));
            answ5.setText(c.getString(9));
            ans_choi = c.getString(10);
            c.close();
        }

        saveBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String jujuju = answer.getText().toString();
                if (jujuju.equals(ans_choi)){
                    Toast.makeText(MoonpulActivity.this, "축하드립니다! 정답입니다.", Toast.LENGTH_LONG).show();
                    finish();
                } else if (!jujuju.equals(ans_choi)) {
                    Toast.makeText(MoonpulActivity.this, "아쉽네요.. 정답은'" + ans_choi + "'번 입니다.", Toast.LENGTH_LONG).show();
                    finish();
                    finish();
                }
            }
        });
    }
}