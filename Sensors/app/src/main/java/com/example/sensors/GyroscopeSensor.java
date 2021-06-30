package com.example.sensors;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
public class GyroscopeSensor extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    TextView tva, tvb, tvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope_sensor);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        tva = (TextView)findViewById(R.id.tvA);
        tvb = (TextView)findViewById(R.id.tvB);
        tvc = (TextView)findViewById(R.id.tvC);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float a = event.values[0];
        float b = event.values[1];
        float c = event.values[2];

        tva.setText(a+"");
        tvb.setText(b+"");
        tvc.setText(c+"");

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