package com.logotet.androlottowheeler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;


public class GeneratedWheelActivity extends ActionBarActivity {
    private Context context;

    private ListView genListView;
    private GeneratedAdapter generatedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_wheel);
        context = getApplicationContext();

        genListView = (ListView) findViewById(R.id.lvGenRow);
        generatedAdapter = new GeneratedAdapter(this);
        genListView.setAdapter(generatedAdapter);
    }

}
