package com.ngyb.othertest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 作者：南宫燚滨
 * 描述：
 * 邮箱：nangongyibin@gmail.com
 * 日期：2020/7/31 13:52
 */
public class LevelActivity extends AppCompatActivity {
    private static final String TAG = "LevelActivity";
    private SensorManager sm;
    private MySensorEventListener mySensorEventListener;
    private Sensor sensor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mySensorEventListener = new MySensorEventListener();
        sensor = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sm.registerListener(mySensorEventListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    public class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            float valueX = event.values[SensorManager.DATA_X];
            float valueY = event.values[SensorManager.DATA_Y];
            float valueZ = event.values[SensorManager.DATA_Z];
            Log.e(TAG, "onSensorChanged: " + valueX + "===" + valueY + "===" + valueZ);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sm.unregisterListener(mySensorEventListener);
    }
}
