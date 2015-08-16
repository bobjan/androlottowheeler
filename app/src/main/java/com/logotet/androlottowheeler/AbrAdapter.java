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
public class AbrAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;

    private ArrayList<Wheel> arrayList;

    public AbrAdapter(Activity activity) {
        this.activity = activity;
        arrayList = AllStatic.wheelSystems;
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
            convertView = inflater.inflate(R.layout.abrrow, null);

        TextView tvBrojBrojeva = (TextView) convertView.findViewById(R.id.tvBrojBrojeva);
        TextView tvGarancija = (TextView) convertView.findViewById(R.id.tvGarancija);
        TextView tvOdPogodaka = (TextView) convertView.findViewById(R.id.tvOdPogodaka);
        TextView tvKombinacija = (TextView) convertView.findViewById(R.id.tvKombinacija);

        Wheel wheel = (Wheel) getItem(position);


        tvBrojBrojeva.setText(wheel.getOdigranihBrojeva() + "");
        tvGarancija.setText(wheel.getGarancija() + "");
        tvOdPogodaka.setText(wheel.getOdIzvucenih() + "");
        tvKombinacija.setText(wheel.getBrojKombinacija() + "");

        return convertView;
    }
}
