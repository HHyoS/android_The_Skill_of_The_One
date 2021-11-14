package com.example.gytkd.the_skill_of_the_one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

public class SaveActivity extends AppCompatActivity {
    String databaseName;
    String tableName;

    Dbhelper dbhelper;
    private SQLiteDatabase db;
    Cursor cursor;
    ArrayAdapter<String> adapter2, adapter3;
    TextView status;

    EditText nameET;
    boolean databaseCreated = false;
    boolean tableCreated = false;

    EditText addField;
    EditText addName;
    Button addButton;
    String qtitle;  //
    String ans; // 답
    EditText Field;
    EditText Qname;
    String nNmae = null;
    String spin = null;
    Button saveQbutton;
    Button saveButton;
    Button nameputput;
    String moonjae;
    String choicece;
    Spinner spinner;
    ArrayAdapter<String> adapter;
    int juge;
    String Qtitle;
    String boonroo;
    String photouri;
    String ex1;
    String ex2;
    String ex3;
    String ex4;
    String ex5;
    String boonboo;
    String juju;
    ArrayList<String> list = new ArrayList<String>();
    String gak = "객관식";
    String dan = "답답형";

    //객관식끝 주관식 필드 시작
    int what;


    boolean isOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        Intent intent = getIntent();

        String fufu = intent.getStringExtra("분류");
        if(intent.getStringExtra("분류").equals("객관식")) {
            what = 0;
            juge = intent.getIntExtra("보기유형",0);
            if (juge == 0)
                photouri = intent.getStringExtra("사진");
            else if (juge == 1)
                moonjae = intent.getStringExtra("문제");
            Qtitle = intent.getStringExtra("문제제목");
            boonroo = intent.getStringExtra("분류");
            ans = intent.getStringExtra("답");
            ex1 = intent.getStringExtra("보기1");
            ex2 = intent.getStringExtra("보기2");
            ex3 = intent.getStringExtra("보기3");
            ex4 = intent.getStringExtra("보기4");
            ex5 = intent.getStringExtra("보기5");
        }
        else if(intent.getStringExtra("분류").equals("단답형")) {
            what = 1;
            juge = intent.getIntExtra("보기유형",0);
            if (juge == 0)
                photouri = intent.getStringExtra("사진");
            else if (juge == 1) {
                moonjae = intent.getStringExtra("문제");
            }
            Qtitle = intent.getStringExtra("문제제목"); // 문제 설명
            boonroo = intent.getStringExtra("분류"); // 단답형 or 개관식
            ans = intent.getStringExtra("답"); // 답
        }

            Field = (EditText) findViewById(R.id.field);// 필드 디스플레이
            spinner = (Spinner) findViewById(R.id.Spinner);
            addField = (EditText) findViewById(R.id.addfield);  // 필드 이름 입력용
            addName = (EditText) findViewById(R.id.QnameInput);  // 이름 입력용
            saveQbutton = (Button) findViewById(R.id.savebutton); // 저장버튼
            addButton = (Button) findViewById(R.id.add); // 필드추가 버튼
            nameputput = (Button) findViewById(R.id.nameSave); // 이름 저장
            Qname = (EditText) findViewById(R.id.name);

        dbhelper = new Dbhelper(this);
        db = dbhelper.getWritableDatabase();
        executeRawQuery(what);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addField.getText().toString().length() !=0){
                    list.add(addField.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        nameputput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nNmae = addName.getText().toString();
                Qname.setText(nNmae);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spin = (String) adapter.getItem(position);
                Field.setText(spin);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //1. 스피너에다가 cursor에 잇는 객관식 필드값 뿌려주기
        //2. 필드명 입력받아서 저장하기
        saveQbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (what == 0) {
                    if (spin == null && nNmae == null)
                        Toast.makeText(getApplicationContext(), "과목과 문제이름이 지정되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    else if (spin == null)
                        Toast.makeText(getApplicationContext(), "과목을 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    else if (nNmae == null)
                        Toast.makeText(getApplicationContext(), " 문제이름을 지정하지 않았습니다,", Toast.LENGTH_SHORT).show();
                    else {
                        Intent intent2 = new Intent(SaveActivity.this, MainActivity.class);
                        boonboo = Field.getText().toString();
                        if (juge == 0) {
                            db.execSQL("insert into choice(named,boonroo,moon,qtitle,exam1,exam2,exam3,exam4,exam5,answer,moonjaewrite,picture) values('" + nNmae + "','" + boonroo + "','" + boonboo + "','" + Qtitle + "','" + ex1 + "','" + ex2 + "','" + ex3 + "','" + ex4 + "','" + ex5 + "','" + ans + "', 'nope' ,'" + photouri + "');");
                            Toast.makeText(getApplicationContext(), ""+ boonboo + " 과목의 "+ nNmae + " 문제가 저장되었습니다", Toast.LENGTH_SHORT).show();
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent2);
                            finish();
                        } else if (juge == 1) {
                            db.execSQL("insert into choice(named,boonroo,moon,qtitle,exam1,exam2,exam3,exam4,exam5,answer,moonjaewrite,picture) values('" + nNmae + "','" + boonroo + "','" + boonboo + "','" + Qtitle + "','" + ex1 + "','" + ex2 + "','" + ex3 + "','" + ex4 + "','" + ex5 + "','" + ans + "','" + moonjae + "','nope');");
                            Toast.makeText(getApplicationContext(), ""+ boonboo + " 과목의 "+ nNmae + " 문제가 저장되었습니다", Toast.LENGTH_SHORT).show();
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent2);
                            finish();
                        }
                    }
                } else if (what == 1) {
                    if (spin == null && nNmae == null)
                        Toast.makeText(getApplicationContext(), "과목과 문제이름이 지정되지 않았습니다.", Toast.LENGTH_SHORT).show();
                        else if(spin == null)
                            Toast.makeText(getApplicationContext(), "과목를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
                        else if (nNmae == null)
                            Toast.makeText(getApplicationContext(), " 문제이름을 지정하지 않았습니다,", Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent2 = new Intent(SaveActivity.this, MainActivity.class);
                            boonboo = Field.getText().toString();
                            if (juge == 0) {
                                db.execSQL("insert into short(named,boonroo,moon,qtitle,answer,moonjaewrite,picture) values('" + nNmae + "','" + boonroo + "','" + boonboo + "','" + Qtitle + "','" + ans + "', 'nope' ,'" + photouri + "');");
                                Toast.makeText(getApplicationContext(), ""+ boonboo + " 과목의 "+ nNmae + " 문제가 저장되었습니다", Toast.LENGTH_SHORT).show();
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent2);
                                finish();
                            } else if (juge == 1) {
                                db.execSQL("insert into short(named,boonroo,moon,qtitle,answer,moonjaewrite,picture) values('" + nNmae + "','" + boonroo + "','" + boonboo + "','" + Qtitle + "','" + ans + "','" + moonjae + "','nope');");
                                Toast.makeText(getApplicationContext(), ""+ boonboo + " 과목의 "+ nNmae + " 문제가 저장되었습니다", Toast.LENGTH_SHORT).show();
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent2);
                                finish();
                        }
                    }
                }
            }
        });

    }


    private void executeRawQuery(int i) {
        if(i==0) {
            cursor = db.rawQuery("select moon FROM choice group by qtitle", null);
            if (cursor.moveToFirst()) {
                list.add(cursor.getString(0));
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
            }
            cursor.close();
        }
        else if(i==1){
            cursor = db.rawQuery("select moon FROM short group by qtitle", null);
            if (cursor.moveToFirst()) {
                list.add(cursor.getString(0));
                while (cursor.moveToNext()) {
                    list.add(cursor.getString(0));
                }
            }
            cursor.close();
        }
    }

    static class Dbhelper extends SQLiteOpenHelper{
        static final String DATABASE_NAME = "make.db";
        static final int DATABASE_VERSION = 16;

        protected Dbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE IF NOT EXISTS choice (_id INTEGER PRIMARY KEY AUTOINCREMENT, named TEXT, boonroo TEXT, moon TEXT, qtitle TEXT, exam1 TEXT, exam2 TEXT, exam3 TEXT, exam4 TEXT, exam5 TEXT, answer TEXT, moonjaewrite TEXT, picture TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS short (_id INTEGER PRIMARY KEY AUTOINCREMENT, named TEXT, boonroo TEXT, moon TEXT, qtitle TEXT, answer TEXT, moonjaewrite TEXT, picture TEXT)");

        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS choice");
            db.execSQL("DROP TABLE IF EXISTS short");
            onCreate(db);
        }
    }
}
