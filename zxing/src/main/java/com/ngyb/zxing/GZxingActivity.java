package com.ngyb.zxing;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitmapUtils;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/5/3 14:43
 */
public class GZxingActivity extends AppCompatActivity {

    private ImageView iv;
    private EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gzxing);
        iv = findViewById(R.id.iv);
        et = findViewById(R.id.et);
    }

    public void g(View view) {
        String str = et.getText().toString().trim();
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this, "数据不能为空！！！！", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Bitmap bitmap = BitmapUtils.create2DCode(str);
                iv.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
