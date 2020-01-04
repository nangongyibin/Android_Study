package com.ngyb.photoorvideo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int mWidth;
    private int mHeight;
    private ImageView mIv;
    private ImageView mIvC;
    private ImageView mIvPalette;
    private Paint mPaint;
    private Bitmap mBitmapC;
    private Bitmap mBitmapC1;
    private ImageView mIvPalettePre;
    private SurfaceView mSfv;
    private VideoView mVv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        WindowManager wm = (WindowManager) getSystemService(Service.WINDOW_SERVICE);
//        Display defaultDisplay = wm.getDefaultDisplay();
//        Point point = new Point();
//        defaultDisplay.getSize(point);
//        mWidth = point.x;
//        mHeight = point.y;
//        mIv = findViewById(R.id.iv);
//        mIvC = findViewById(R.id.ivC);
//        copyBitmap();
//        mIvPalette = findViewById(R.id.ivPalette);
//        mIvPalettePre = findViewById(R.id.ivPalettePre);
//        initPalette();
//        initMosatsu();
//        initSfv();
//        initVideoView();
    }

    private void initVideoView() {
        try {
            mVv = findViewById(R.id.vv);
            mVv.setVideoPath("http://it.nangongyibin.com:8080/resource/cc.mp4");
            mVv.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initSfv() {
        mSfv = findViewById(R.id.sfv);
        SurfaceHolder holder = mSfv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            private MediaPlayer mMediaPlayer;
            private int currentPosition;
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mMediaPlayer = new MediaPlayer();
                    mMediaPlayer.setDataSource("http://it.nangongyibin.com:8080/resource/cc.mp4");
                    mMediaPlayer.prepareAsync();
                    mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                            mp.seekTo(currentPosition);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (mSfv!=null && mMediaPlayer.isPlaying()){
                    currentPosition = mMediaPlayer.getCurrentPosition();
                    mMediaPlayer.stop();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    private void initMosatsu() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pre19);
        final Bitmap bitmapPre = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        final Canvas canvas = new Canvas(bitmapPre);
        canvas.drawBitmap(bitmap, new Matrix(), paint);
        mIvPalettePre.setImageBitmap(bitmapPre);
        mIvPalettePre.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "onTouch: UP" );
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "onTouch: DOWN" );
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "onTouch: MOVE" );
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 20; j++) {
                                try {
                                    bitmapPre.setPixel((int)event.getX()+i,(int)event.getY()+j, Color.TRANSPARENT);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        mIvPalettePre.setImageBitmap(bitmapPre);
                        break;

                }
                return true;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initPalette() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bg);
        mBitmapC1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        mPaint = new Paint();
        final Canvas canvas = new Canvas(mBitmapC1);
        canvas.drawBitmap(bitmap, new Matrix(), mPaint);
        mIvPalettePre.setImageBitmap(mBitmapC1);
        mIvPalettePre.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float endX = event.getX();
                        float endY = event.getY();
                        canvas.drawLine(startX, startY, endX, endY, mPaint);
                        mIvPalettePre.setImageBitmap(mBitmapC1);
                        startX = endX;
                        startY = endY;
                        break;
                }
                return true;
            }
        });
    }

    private void copyBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.tomcat);
        mIv.setImageBitmap(bitmap);
        mBitmapC = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(mBitmapC);
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        matrix.postTranslate(0, bitmap.getHeight());
        canvas.drawBitmap(bitmap, matrix, paint);
        mIvC.setImageBitmap(mBitmapC);

    }

    public void load(View view) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.big, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int scaleY = outHeight / mHeight;
        int scaleX = outWidth / mWidth;
        int scale;
        if (scaleX > scaleY) {
            scale = scaleX;
        } else {
            scale = scaleY;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.big, options);
        mIv.setImageBitmap(bitmap);
    }

    public void changeColor(View view) {
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    public void changeThickness(View view) {
        mPaint.setStrokeWidth(25f);
    }

    public void save(View view) {
        try {
            File file = new File(getFilesDir().getAbsolutePath() + File.separator + "a.png");
            FileOutputStream fos = new FileOutputStream(file);
            boolean compress = mBitmapC1.compress(Bitmap.CompressFormat.PNG, 25, fos);
            if (compress) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void net(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource("http://it.nangongyibin.com:8080/resource/xpg.mp3");
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            Log.e(TAG, "onPrepared: 准备好了" );
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    Log.e(TAG, "run: "+e.getLocalizedMessage() );
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void playPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(getFilesDir().getPath(), "1.png");
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,7219);
    }

    public void playVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = new File(getFilesDir().getPath(), "2.3pg");
        Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,9127);
    }
}
