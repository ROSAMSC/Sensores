package com.example.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listOfSensors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfSensors = (ListView) findViewById(R.id.listOfSensors);
        drawList();
    }

    public void drawList () {
        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> devices = sm.getSensorList(Sensor.TYPE_ALL);
        ArrayList<SensorElement> listSensorsElement = new ArrayList<>();

        Toast.makeText(getApplicationContext(), "Numero de sensores: " + devices.size(), Toast.LENGTH_SHORT).show();
        for (Sensor element :devices) {
            SensorElement e = new SensorElement();
            e.name = element.getName();
            e.vendor = element.getVendor();
            e.type = (element.getStringType());
            e.version = element.getVersion() + "";
            e.typeInt = element.getType();
            listSensorsElement.add(e);
        }

        ListViewAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.sensor_element, listSensorsElement);
        listOfSensors.setAdapter(adapter);
        listOfSensors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SensorElement sensorElement = listSensorsElement.get(position);
                //Toast.makeText(getApplicationContext(), sensorElement.name, Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (sensorElement.typeInt) {
                    case Sensor.TYPE_LIGHT:
                        intent = new Intent(getApplicationContext(), LightSensor.class);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_PROXIMITY:
                        intent = new Intent(getApplicationContext(), ProximitySensor.class);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_ACCELEROMETER:
                        intent = new Intent(getApplicationContext(), SensorAccelerometer.class);
                        startActivity(intent);
                    case Sensor.TYPE_GYROSCOPE:
                        intent = new Intent(getApplicationContext(), GyroscopeSensor.class);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_GRAVITY:
                        intent = new Intent(getApplication(), GravitySensor.class);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        intent = new Intent(getApplicationContext(), MagneticSensor.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }
}