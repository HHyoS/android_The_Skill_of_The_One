package com.example.gytkd.the_skill_of_the_one;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class M_ChoiceActivity extends AppCompatActivity {

    private ImageView imav;
    private Uri imgUri, photoURI, albumURI;

    private String mCurrentPhotoPath;

    private static final int FROM_CAMERA = 0;

    private static final int FROM_ALBUM = 1;
    EditText edt;
    EditText Question;
    EditText example;
    EditText answer;
    EditText answ1;
    EditText answ2;
    EditText answ3;
    EditText answ4;
    EditText answ5;
    String qtitle;  //
    String ans_choi; // 답
    Bitmap bm1;
    Bitmap bm2;
    String moonjae;
    int juge;


    String Classification = "객관식";
    String example1;
    String example2;
    String example3;
    String example4;
    String example5;
    String photous;
    String moonjaewrite;
    //문제보기 5개
    SQLiteDatabase db;

    int asave=3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch_ans);
        final int permissionCheck0 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        final int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        final int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        answ1 = (EditText) findViewById(R.id.answ1);
        answ2 = (EditText) findViewById(R.id.answ2);
        answ3 = (EditText) findViewById(R.id.answ3);
        answ4 = (EditText) findViewById(R.id.answ4);
        answ5 = (EditText) findViewById(R.id.answ5);
        Question = (EditText) findViewById(R.id.questionET);
        answer = (EditText) findViewById(R.id.answer);
        imav = (ImageView) findViewById(R.id.imageV);
        edt = (EditText) findViewById(R.id.write);
        Button saveBt = (Button) findViewById(R.id.saveBt);
        Button choiceBt = (Button) findViewById(R.id.choiBt);
        Button writeBt = (Button) findViewById(R.id.writeBt);


        if (permissionCheck0 == PackageManager.PERMISSION_GRANTED || permissionCheck1 == PackageManager.PERMISSION_GRANTED || permissionCheck2 == PackageManager.PERMISSION_GRANTED) {
        } else {
            Toast.makeText(this, "카메라 및 앨범 수신권한 필요", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "카메라 및 앨범 수신권한 설명필요", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }


        saveBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(M_ChoiceActivity.this, SaveActivity.class);
                if (asave == 0) {

                    photous = photoURI.toString();
                    qtitle = Question.getText().toString();
                    ans_choi = answer.getText().toString();
                    example1 = answ1.getText().toString();
                    example2 = answ2.getText().toString();
                    example3 = answ3.getText().toString();
                    example4 = answ4.getText().toString();
                    example5 = answ5.getText().toString();
                    juge = 0;

                    intent.putExtra("보기유형", juge);
                    intent.putExtra("분류", Classification);
                    intent.putExtra("사진", photous);
                    intent.putExtra("문제제목", qtitle);
                    intent.putExtra("답", ans_choi);
                    intent.putExtra("보기1", example1);
                    intent.putExtra("보기2", example2);
                    intent.putExtra("보기3", example3);
                    intent.putExtra("보기4", example4);
                    intent.putExtra("보기5", example5);

                    startActivity(intent);
                } else if (asave == 1) {
                    juge = 1;
                    moonjae = edt.getText().toString();
                    intent.putExtra("분류", Classification);
                    intent.putExtra("문제", moonjae);
                    qtitle = Question.getText().toString();
                    ans_choi = answer.getText().toString();
                    example1 = answ1.getText().toString();
                    example2 = answ2.getText().toString();
                    example3 = answ3.getText().toString();
                    example4 = answ4.getText().toString();
                    example5 = answ5.getText().toString();

                    intent.putExtra("보기유형", juge);
                    intent.putExtra("문제제목", qtitle);
                    intent.putExtra("답", ans_choi);
                    intent.putExtra("보기1", example1);
                    intent.putExtra("보기2", example2);
                    intent.putExtra("보기3", example3);
                    intent.putExtra("보기4", example4);
                    intent.putExtra("보기5", example5);
                    startActivity(intent);
                }
                else if(asave==3){
                    Toast.makeText(M_ChoiceActivity.this, "아직 문제를 만들 준비가 안되셨군요!", Toast.LENGTH_LONG).show();
                }
            }
        });
        choiceBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                asave = 0;
                imav.setVisibility(View.VISIBLE);
                edt.setVisibility(View.INVISIBLE);

                showMessage();

            }
        });
        writeBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                asave = 1;
                edt.setVisibility(View.VISIBLE);
                imav.setVisibility(View.INVISIBLE);
            }
        });
    }

        public void doTakePhotoAction () {

            // 촬영 후 이미지 가져옴

            String state = Environment.getExternalStorageState();



            if (Environment.MEDIA_MOUNTED.equals(state)) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (intent.resolveActivity(getPackageManager()) != null) {

                    File photoFile = null;

                    try {

                        photoFile = createImageFile();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                    if (photoFile != null) {

                        Uri providerURI = FileProvider.getUriForFile(this, "com.example.gytkd.the_skill_of_the_one", photoFile);

                        imgUri = providerURI;

                        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, providerURI);

                        startActivityForResult(intent, FROM_CAMERA);

                    }

                }

            } else {

                Log.v("알림", "저장공간에 접근 불가능");

                return;

            }


        }

        public void doTakeAlbumAction () {
            Intent intent = new Intent(Intent.ACTION_PICK);

            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            startActivityForResult(intent, FROM_ALBUM);
        }

        protected void onActivityResult ( int requestCode, int resultCode, Intent data){

            super.onActivityResult(requestCode, resultCode, data);


            if (resultCode != RESULT_OK) {

                return;

            }

            switch (requestCode) {

                case FROM_ALBUM: {

                    //앨범에서 가져오기

                    if (data.getData() != null) {

                        try {

                            File albumFile = null;

                            albumFile = createImageFile();

                            photoURI = data.getData();

                            albumURI = Uri.fromFile(albumFile);


                            galleryAddPic();

                            bm1 = MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                            imav.setImageBitmap(bm1);


                        } catch (Exception e) {

                            e.printStackTrace();

                            Log.v("알림", "앨범에서 가져오기 에러");

                        }

                    }

                    break;

                }


                case FROM_CAMERA: {

                    try {

                        Log.v("알림", "FROM_CAMERA 처리");

                        galleryAddPic();

//이미지뷰에 이미지셋팅
                        bm2 = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                        photoURI = imgUri;
                        imav.setImageBitmap(bm2);


                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                    break;

                }


            }

        }
        public void galleryAddPic () {

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

            File f = new File(mCurrentPhotoPath);

            Uri contentUri = Uri.fromFile(f);

            mediaScanIntent.setData(contentUri);

            sendBroadcast(mediaScanIntent);

            Toast.makeText(this, "사진이 저장되었습니다", Toast.LENGTH_SHORT).show();
        }

        private void showMessage () {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakePhotoAction();
                }
            };
            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    doTakeAlbumAction();
                }
            };
            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            builder.setTitle("등록할 이미지 선택");
            builder.setPositiveButton("사진촬영", cameraListener);
            builder.setNeutralButton("앨범선택", albumListener);
            builder.setNegativeButton("취소", cancelListener);
            builder.show();
        }

        public File createImageFile () throws IOException {

            String imgFileName = System.currentTimeMillis() + ".jpg";

            File imageFile = null;

            File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "ireh");


            if (!storageDir.exists()) {

                Log.v("알림", "storageDir 존재 x " + storageDir.toString());

                storageDir.mkdirs();

            }

            Log.v("알림", "storageDir 존재함 " + storageDir.toString());

            imageFile = new File(storageDir, imgFileName);

            mCurrentPhotoPath = imageFile.getAbsolutePath();


            return imageFile;

        }

    }



