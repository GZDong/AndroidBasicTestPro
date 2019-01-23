package com.gzd.example.testbasicandroid;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    public static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            TextView saveText = (TextView)findViewById(R.id.text_save_instance_message);
            saveText.setText(savedInstanceState.getString("RestoreMsg"));
        }

        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("openSecondActivity");
                intent.addCategory("openSecondActivityWithThis");
                intent.putExtra("token","I'am MainAct");
                startActivityForResult(intent,101);
            }
        });

        Button btnProRound = findViewById(R.id.btn_progress_bar);
        final ProgressBar proBar = findViewById(R.id.progress_round);
        btnProRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proBar.getVisibility() == View.VISIBLE){
                    proBar.setVisibility(View.GONE);
                }else {
                    proBar.setVisibility(View.VISIBLE);
                }
            }
        });

        Button btnProHor = findViewById(R.id.btn_progress_line);
        final ProgressBar proLine = findViewById(R.id.progress_horizon);
        btnProHor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (proLine.getProgress() <= 100){
                    proLine.setProgress(proLine.getProgress() + 11);
                }else{
                    proLine.setProgress(-1);
                }
            }
        });

        Button btnImage = findViewById(R.id.btn_image);
        final ImageView imageView = findViewById(R.id.image_test);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });

        Button btnShowDialogWithViewDialog = findViewById(R.id.btn_show_view_dialog);
        btnShowDialogWithViewDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("alertDialog");
                alertDialog.setIcon(R.drawable.ic_launcher_foreground);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.show();
            }
        });

        Button btnShowDialogWithActivity = findViewById(R.id.btn_show_act_dialog);
        btnShowDialogWithActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogActivity.actionStart(MainActivity.this,"activity dialog");
            }
        });

        Button btnShowDialogWithProgress = findViewById(R.id.btn_show_pro_dialog);
        btnShowDialogWithProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("pro dialog");
                dialog.setIcon(R.drawable.ic_launcher_foreground);
                dialog.show();
            }
        });


        Button btnListView = findViewById(R.id.btn_listview);
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListviewActivity.actionStart(MainActivity.this,"go to list view");
            }
        });

        Button btnRecView = findViewById(R.id.btn_recycler_view);
        btnRecView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerActivity.actionStart(MainActivity.this,"go to list view");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
/*      inflate可以理解为填充的意思，这里获取填菜单充器（系统已经创建的填充类），指定填充内容和填充对象，
        填充对象被菜单填充器指定为菜单类，来自Activity创建时，系统创建并传入的菜单对象，该方法在展示时会判断返回值，
        如果为true代表需要展示*/
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add:
                Toast.makeText(this,"Add",Toast.LENGTH_LONG).show();
                break;
            case R.id.item_remove:
                SecondActivity.actionStart(this,"go to second place");
                break;
            case R.id.item_close:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 101:
                if (resultCode == RESULT_OK) {
                    String msg = data.getStringExtra("return_result");
                    Log.e(TAG, "onActivityResult: OK with_" + msg);
                }else {
                    Log.e(TAG, "onActivityResult: cancel"  );
                }
            break;
            default:
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("RestoreMsg","saveTextIfStopActivityIsRestored");
    }
}
