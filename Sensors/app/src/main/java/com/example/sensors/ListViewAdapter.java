package com.example.sensors;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
public class ListViewAdapter extends ArrayAdapter<SensorElement> {
    private final int resourceLayout;
    private final Context context;


    public ListViewAdapter(@NonNull Context context, int resource, ArrayList<SensorElement> listOfSensors) {
        super(context, resource, listOfSensors);
        this.context = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            convertView = vi.inflate(resourceLayout, null);
        }

        SensorElement e = getItem(position);
        if (e!=null) {
            TextView tvName = convertView.findViewById(R.id.tvName);
            TextView tvType = convertView.findViewById(R.id.tvType);
            TextView tvVendor = convertView.findViewById(R.id.tvVendor);
            TextView tvVersion = convertView.findViewById(R.id.tvVersion);

            tvName.setText(e.name);
            tvType.setText(e.type);
            tvVendor.setText(e.vendor);
            tvVersion.setText(e.version);
        }

        return convertView;
    }
}
