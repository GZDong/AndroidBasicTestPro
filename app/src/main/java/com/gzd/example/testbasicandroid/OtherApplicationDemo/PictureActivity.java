package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gzd.example.testbasicandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PictureActivity extends AppCompatActivity {

    public final static int TAKE_CODE = 1;
    public final static int TAKE_PICTURE = 2;
    private Uri imgUri;
    File outputFile;
    ImageView imgTake;
    ImageView imgChoose;
    File rooFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Button btnTake = findViewById(R.id.btn_take);
        Button btnChoose = findViewById(R.id.btn_choose_pic);
        imgTake = findViewById(R.id.img_take);
        imgChoose = findViewById(R.id.img_from_pics);

        rooFile = new File(getExternalCacheDir(),"images");
        if (!rooFile.exists()){
            rooFile.mkdir();
        }
        File file = new File(rooFile,"capture.jpg");
        if (file.exists()){
            imgTake.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
        }

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //传递一个URI，这个URI是由File对象组成，取的话是从相机程序提供的provider中取的
                outputFile = new File(rooFile, "capture_new.jpg");
                if (outputFile.exists()){
                    outputFile.delete();
                }
                try {
                    outputFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >=24){
                    imgUri = FileProvider.getUriForFile(PictureActivity.this,"fake.path.tag",outputFile);
                }else {
                    imgUri = Uri.fromFile(outputFile);//这里使用的是我们上面创建File时指定的真实路径作为authority
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
                startActivityForResult(intent,TAKE_CODE);
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(PictureActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(PictureActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},TAKE_PICTURE);
                }else{
                    openAlbum();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_CODE:
                if (resultCode == RESULT_OK){
                    try {
                        imgTake.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    File file = new File(rooFile,"capture.jpg");
                    if (file.exists()){
                        file.delete();
                    }
                    outputFile.renameTo(file);
                }
            break;
            case TAKE_PICTURE:
                //get picture from intent
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT >= 19){
                        handleImageNow(data);
                    }else{
                        handleImageBefore(data);
                    }
                }
            default:
        }
    }

    private void handleImageBefore(Intent data) {
        Uri uri  = data.getData();
        //这时候的uri没有经过封装
        String imagePath = uri.getPath();
        imgChoose.setImageBitmap(BitmapFactory.decodeFile(imagePath));
    }

    @TargetApi(19)    //确保是某个api下被使用
    private void handleImageNow(Intent data) {
        Uri uri  = data.getData();
        //这时候的Uri经过provider的封装，需要解析提取
        String imagePath = null;

        if (DocumentsContract.isDocumentUri(this,uri)){
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            imagePath = uri.getPath();
        }
        //displayImage
        displayImage(imagePath);
    }

    private void displayImage(String imagePath) {
        if (imagePath != null){
            imgChoose.setImageBitmap(BitmapFactory.decodeFile(imagePath));
        }else {
            Toast.makeText(this,"failed",Toast.LENGTH_LONG).show();
        }
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(externalContentUri,null,selection,null,null);
        if (cursor != null){
            if ((cursor.moveToFirst())){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
            break;
            default:
        }
    }

    public void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");  //get content
        intent.setType("image/*");
        startActivityForResult(intent,TAKE_PICTURE);
    }
}
