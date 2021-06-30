package com.example.sensors;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
public class LightSensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private ProgressBar progressBar;
    private ImageView brightnessIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_sensor);
        progressBar = (ProgressBar)findViewById(R.id.barLight);
        brightnessIcon = (ImageView)findViewById(R.id.brightnessIcon);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];
        TextView tvLight = (TextView)findViewById(R.id.tvLight);
        tvLight.setText(sensorValue+"");
        int porcent = (int)sensorValue*100/1000;
        progressBar.setProgress(porcent);
        if(porcent > 33) {
            if (porcent > 66) {
                brightnessIcon.setImageDrawable(getDrawable(R.drawable.ic_high));
            }else {
                brightnessIcon.setImageDrawable(getDrawable(R.drawable.ic_medium));
            }
        } else {
            brightnessIcon.setImageDrawable(getDrawable(R.drawable.ic_low));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume () {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause () {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}