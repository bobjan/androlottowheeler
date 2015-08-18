package com.logotet.androlottowheeler;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.Kombinacija;

import java.util.ArrayList;


/**
 * Created by boban on 8/18/15.
 */
public class GeneratedAdapter extends BaseAdapter {
    private static final String TAG = "GeneratedAdapter";

    private LayoutInflater inflater;
    private Activity activity;
    ArrayList arrayList;

    public GeneratedAdapter(Activity activity) {
        this.activity = activity;
        arrayList = AllStatic.mappedSistem.getKombinacije();
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
            convertView = inflater.inflate(R.layout.genrow, null);

        TextView tvGenerKomb = (TextView) convertView.findViewById(R.id.tvGenerated);

        Kombinacija k = (Kombinacija) getItem(position);

        tvGenerKomb.setText(k.toString());


        return convertView;
    }
}
