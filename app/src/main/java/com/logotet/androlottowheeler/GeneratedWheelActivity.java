package com.logotet.androlottowheeler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.logotet.androlottowheeler.model.AllStatic;


public class GeneratedWheelActivity extends ActionBarActivity {
    private static final String TAG = "GeneratedWheelActivity";

    private ListView genListView;
    private GeneratedAdapter generatedAdapter;
    Button btnProceedMail;

    Intent proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_wheel);

        genListView = (ListView) findViewById(R.id.lvGenRow);
        generatedAdapter = new GeneratedAdapter(this);
        genListView.setAdapter(generatedAdapter);


        SharedPreferences prefs = getSharedPreferences(AllStatic.MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("fullwheel", AllStatic.fullWheel);
        editor.commit();


        btnProceedMail = (Button) findViewById(R.id.btnProceedEmail);
        proceed = new Intent(this,SendEmailActivity.class);
        btnProceedMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(proceed);
            }
        });


    }

}
