package com.gzd.example.testbasicandroid.OtherApplicationDemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gzd.example.testbasicandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PictureActivity extends AppCompatActivity {

    public final static int TAKE_CODE = 1;
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

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_CODE:
                //get picture from intent
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
            default:
        }
    }
}
