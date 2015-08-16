package com.logotet.androlottowheeler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.MyNumberCell;
import com.logotet.androlottowheeler.threads.FullWheelMaker;
import com.logotet.androlottowheeler.threads.WheelsFactory;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    NumberPicker numPicker = null;
    Button btnContinue;
    Intent akcija;
    RadioGroup radioGroup;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startFromPreferences();
        testMetrics();


        numPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numPicker.setMaxValue(99);
        numPicker.setMinValue(20);
        numPicker.setValue(AllStatic.lottoSize);
        numPicker.setWrapSelectorWheel(false);

        radioGroup = (RadioGroup)findViewById(R.id.rg_lottoType);

        switch(AllStatic.numbersDrawn){
            case 5:
                radioGroup.check(R.id.rb_lotto5);
                break;
            case 7:
                radioGroup.check(R.id.rb_lotto7);
                break;
            default:
                radioGroup.check(R.id.rb_lotto6);
                break;
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_lotto5) {
                    AllStatic.numbersDrawn = 5;
                }
                if (checkedId == R.id.rb_lotto6) {
                    AllStatic.numbersDrawn = 6;
                }
                if (checkedId == R.id.rb_lotto7) {
                    AllStatic.numbersDrawn = 7;
                }
            }
        });



//        akcija = new Intent(this, GridActivity.class);
//        akcija = new Intent(this, SelectionActivity.class);
        akcija = new Intent(this, TestTabActivity.class);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllStatic.lottoSize = numPicker.getValue();
                for (int i = 0; i < AllStatic.lottoSize; i++) {
                    MyNumberCell myb = new MyNumberCell();
                    myb.setValue(i + 1);
                    myb.setPicked(false);
                    AllStatic.tiket.add(myb);
                }

                setPreferences();

                FullWheelMaker fwm = new FullWheelMaker();
                fwm.start();

                WheelsFactory fact = new WheelsFactory();
                fact.start();

                startActivity(akcija);
            }
        });
    }

    private void setPreferences() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("numdrawn", AllStatic.numbersDrawn);
        editor.putInt("lottosize", AllStatic.lottoSize);
        editor.commit();
    }

    private void startFromPreferences(){
        prefs = getSharedPreferences(AllStatic.MY_PREFS_NAME, MODE_PRIVATE);
        AllStatic.numbersDrawn = prefs.getInt("numdrawn", 6);
        AllStatic.email = prefs.getString("email", "");
        AllStatic.lottoSize = prefs.getInt("lottosize", 49);
        AllStatic.fullWheel = prefs.getBoolean("fullwheel", false);
//        Log.w(TAG,"numdrawn = " + AllStatic.numbersDrawn);
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
//        Log.w(TAG,"width = " + AllStatic.width + "dp");
//        Log.w(TAG,"height = " + AllStatic.height + "dp" );
//        Log.w(TAG,"density = " + dens );
    }
}
