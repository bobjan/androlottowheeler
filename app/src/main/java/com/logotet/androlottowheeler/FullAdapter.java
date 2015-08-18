package com.logotet.androlottowheeler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.Wheel;

import java.util.ArrayList;

/**
 * Created by boban on 8/15/15.
 */
public class FullAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    ArrayList<Wheel> arrayList;

    public FullAdapter(Activity activity) {
        this.activity = activity;
        arrayList = AllStatic.fullSystems;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.fullrow, null);

        TextView tvfBroj = (TextView) convertView.findViewById(R.id.tvfBrojBrojeva);
        TextView tvfKombinacija = (TextView) convertView.findViewById(R.id.tvfKombinacija);

        Wheel wheel = (Wheel) getItem(position);
        tvfBroj.setText(wheel.getSelectionSize() + "");
        tvfKombinacija.setText(wheel.getBrojKombinacija() + "");
        return convertView;
    }
}
