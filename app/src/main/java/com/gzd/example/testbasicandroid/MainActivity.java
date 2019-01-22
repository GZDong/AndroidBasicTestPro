package com.gzd.example.testbasicandroid;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
