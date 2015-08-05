package com.logotet.androlottowheeler;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.MyButton;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    NumberPicker numPicker = null;
    Button btnContinue;
    Intent akcija;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startFromPreferences();

        numPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numPicker.setMaxValue(99);
        numPicker.setMinValue(20);
        numPicker.setValue(49);
        numPicker.setWrapSelectorWheel(false);
    testMetrics();
        numPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.w(TAG, "old = " + oldVal + "\t" + "new = " + newVal);

            }
        });
        ///

        for (int i = 0; i < AllStatic.maxNumbers; i++) {
            MyButton myb = new MyButton();
            myb.setValue(i + 1);
            myb.setPicked(false);
            if((i % 3) == 0)
                myb.setPicked(true);
            AllStatic.tiket.add(myb);
        }

        ///

        akcija = new Intent(this, GridActivity.class);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(akcija);
            }
        });
    }

    private void startFromPreferences(){
        AllStatic.gameSize = 6;
        AllStatic.maxNumbers = 49;
        AllStatic.systemSize = 10;
    }

    private void testMetrics(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double width=(double)dm.widthPixels;
        double height=(double)dm.heightPixels;
        double dens=(double)dm.densityDpi;
        AllStatic.width = (int)(width * 160.0/dens);
        AllStatic.height = (int)(height * 160.0/dens);
        AllStatic.pixelWidth = (int) width;
        AllStatic.pixelHeight = (int)height;
        Log.w(TAG,"width = " + AllStatic.width + "dp");
        Log.w(TAG,"height = " + AllStatic.height + "dp" );
        Log.w(TAG,"density = " + dens );
    }
}
