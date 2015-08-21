package com.logotet.androlottowheeler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.Wheel;


public class TwoTabbedActivity extends AppCompatActivity {
    Button btnFull;
    Button btnAbr;


    LinearLayout llFull;
    LinearLayout llAbr;


    private Context context;
    FullAdapter fullAdapter;
    AbrAdapter abrAdapter;

    private ListView abrListView;
    private ListView fullListView;


    private int clrSelected;
    private int clrDeselected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twotabbed);
        context = getApplicationContext();

        clrSelected = getResources().getColor(R.color.white);
        clrDeselected = getResources().getColor(R.color.grey);


        btnFull = (Button) findViewById(R.id.btnFull);
        btnAbr = (Button) findViewById(R.id.btnAbr);

        llFull = (LinearLayout) findViewById(R.id.llFull);
        llAbr = (LinearLayout) findViewById(R.id.llAbr);


        if (AllStatic.fullWheel) {
            llFull.setVisibility(View.VISIBLE);
            llAbr.setVisibility(View.GONE);
            btnAbr.setBackgroundColor(clrDeselected);
            btnFull.setBackgroundColor(clrSelected);
        } else {
            llFull.setVisibility(View.GONE);
            llAbr.setVisibility(View.VISIBLE);
            btnFull.setBackgroundColor(clrDeselected);
            btnAbr.setBackgroundColor(clrSelected);
        }


        abrListView = (ListView) findViewById(R.id.lvAbr);
        fullListView = (ListView) findViewById(R.id.lvFull);

        abrAdapter = new AbrAdapter(this);
        fullAdapter = new FullAdapter(this);

        abrListView.setAdapter(abrAdapter);
        fullListView.setAdapter(fullAdapter);

        btnAbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFull.setVisibility(View.GONE);
                llAbr.setVisibility(View.VISIBLE);
                AllStatic.fullWheel = false;
                btnFull.setBackgroundColor(clrDeselected);
                btnAbr.setBackgroundColor(clrSelected);
            }
        });


        btnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFull.setVisibility(View.VISIBLE);
                llAbr.setVisibility(View.GONE);
                AllStatic.fullWheel = true;
                btnAbr.setBackgroundColor(clrDeselected);
                btnFull.setBackgroundColor(clrSelected);
            }
        });


        abrListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wheel wheel = (Wheel) abrAdapter.getItem(position);
                AllStatic.selectedWheel = wheel;
                AllStatic.clearAll();
                Intent intent = new Intent(context, GridActivity.class);
                startActivity(intent);
            }
        });
        fullListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Wheel wheel = (Wheel) fullAdapter.getItem(position);
                AllStatic.selectedWheel = wheel;
                AllStatic.clearAll();
                Intent intent = new Intent(context, GridActivity.class);
                startActivity(intent);
            }
        });
    }
}
