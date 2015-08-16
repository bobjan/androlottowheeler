package com.logotet.androlottowheeler;

import android.app.ActionBar;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;


public class TestTabActivity extends AppCompatActivity {
    Button btnFull;
    Button btnAbr;


    LinearLayout llFull;
    LinearLayout llAbr;


    private Context context;
    FullAdapter fullAdapter;
    AbrAdapter abrAdapter;

    private ListView abrListView;
    private ListView fullListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab);
        context = getApplicationContext();



        btnFull = (Button) findViewById(R.id.btnFull);
        btnAbr = (Button) findViewById(R.id.btnAbr);

        llFull = (LinearLayout) findViewById(R.id.llFull);
        llAbr = (LinearLayout) findViewById(R.id.llAbr);



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
            }
        });


        btnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llFull.setVisibility(View.VISIBLE);
                llAbr.setVisibility(View.GONE);
            }
        });
    }
}
