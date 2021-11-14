package com.example.gytkd.the_skill_of_the_one;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MoonjaeActivity extends AppCompatActivity {
    SingerAdapter adapter = new SingerAdapter();
    ListView listview;
    ArrayList<String> items;
    SaveActivity.Dbhelper dbhelper;
    SQLiteDatabase db;

    String named;
    String boon;
    String qtitle;
    Integer a;
    Integer num;
    String K;
    String booon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moonjae);
        dbhelper = new SaveActivity.Dbhelper(this);
        db = dbhelper.getWritableDatabase();
        listview = (ListView) findViewById(R.id.listView);

        Cursor c = db.rawQuery("SELECT _id,named,boonroo,moon,qtitle  FROM choice", null);
        Cursor c1 = db.rawQuery("SELECT _id,named,boonroo,moon,qtitle  FROM short", null);
            if (c.moveToFirst()) {
                a = c.getInt(0);
                named = c.getString(1);
                K = c.getString(3);
                boon = c.getString(2);
                qtitle = c.getString(4);
                {
                    adapter.addItem(new SingerItem(named, boon, qtitle, a,K));
                    while (c.moveToNext()) {
                        a = c.getInt(0);
                        named = c.getString(1);
                        K = c.getString(3);
                        boon = c.getString(2);
                        qtitle = c.getString(4);
                        adapter.addItem(new SingerItem(named, boon, qtitle, a,K));
                    }
                }
            }
            else if(!c.moveToFirst()){
                c.close();}

            if (c1.moveToFirst()) {
            a = c1.getInt(0);
            named = c1.getString(1);
            K = c1.getString(3);
            boon = c1.getString(2);
            qtitle = c1.getString(4);
            {
                adapter.addItem(new SingerItem(named, boon, qtitle, a,K));
                while (c1.moveToNext()) {
                    a = c1.getInt(0);
                    named = c1.getString(1);
                    K = c1.getString(3);
                    boon = c1.getString(2);
                    qtitle = c1.getString(4);
                    adapter.addItem(new SingerItem(named, boon, qtitle, a,K));
                }
            }
        }
            else if(!c.moveToFirst()){
                Toast.makeText(this, "표시할 문제가 없습니다.", Toast.LENGTH_SHORT).show();
                c1.close();
                finish();}
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                num = item.getNum();
                booon = item.getBoon();
                showMessage();
            }
        });
        }

        class SingerAdapter extends BaseAdapter {
            ArrayList<SingerItem> items = new ArrayList<SingerItem>();

            public int getCount() {
                return items.size();
            }

            public void addItem(SingerItem item) {
                items.add(item);
            }

            public Object getItem(int posiition) {
                return items.get(posiition);
            }

            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup viewGroup) {
                SingerItemView view = new SingerItemView(getApplicationContext());
                SingerItem item = items.get(position);
                view.setName(item.getName());
                view.setBoon(item.getBoon());
                view.setQ(item.getQ());
                view.setK(item.getK());

                return view;
            }
        }
    private void showMessage () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener viewListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent10;
                if(booon.equals("객관식")){
                   intent10 = new Intent(MoonjaeActivity.this, ModifiedActivity.class);
                intent10.putExtra("id",num);
                intent10.putExtra("분류",booon);
                startActivity(intent10);}
                else if(booon.equals("단답형")){
                    intent10 = new Intent(MoonjaeActivity.this, ModifiedChActivity.class);
                    intent10.putExtra("id",num);
                    intent10.putExtra("분류",booon);
                    startActivity(intent10);
                }
            }
        };
        DialogInterface.OnClickListener delectListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(boon.equals("객관식")){
                db.execSQL("delete from choice where _id= "+num+";");
                finish();
                startActivity(new Intent(MoonjaeActivity.this, MoonjaeActivity.class));}
                else if(boon.equals("단답형")){
                    db.execSQL("delete from short where _id= "+num+";");
                    finish();
                    startActivity(new Intent(MoonjaeActivity.this, MoonjaeActivity.class));}
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        builder.setTitle("어떤 항목을 이용하시겠습니까");
        builder.setPositiveButton("문제 조회/수정", viewListener);
        builder.setNeutralButton("문제 삭제", delectListener);
        builder.setNegativeButton("취소", cancelListener);
        builder.show();
    }
    }
