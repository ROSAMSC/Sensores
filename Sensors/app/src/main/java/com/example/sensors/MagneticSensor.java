package com.example.sensors;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class MagneticSensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    TextView tga, tgb, tgc, mt;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnetic_sensor);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        tga = (TextView)findViewById(R.id.tga);
        tgb = (TextView)findViewById(R.id.tgb);
        tgc = (TextView)findViewById(R.id.tgc);
        mt = (TextView)findViewById(R.id.magnetText);
        pb = (ProgressBar)findViewById(R.id.magnetBar);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float a = event.values[0];
        float b = event.values[1];
        float c = event.values[2];

        tga.setText(a+"");
        tgb.setText(b+"");
        tgc.setText(c+"");

        double result = Math.sqrt(a*a + b*b + c*c);
        mt.setText(result + " Microteslas");
        pb.setProgress((int)result*100/600);
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