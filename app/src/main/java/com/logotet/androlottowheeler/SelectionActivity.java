package com.logotet.androlottowheeler;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;


public class SelectionActivity extends ActionBarActivity implements ActionBar.TabListener {
    private static final String TAG = "SelectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        ActionBar.Tab tab = actionBar.newTab();
//        tab.setText("Full wheels " );
//        tab.setTabListener(this);
//        actionBar.addTab(tab);
//
//        tab.setText("Abbreviated wheels " );
//        tab.setTabListener(this);
//        actionBar.addTab(tab);


 // ovo treba da ide u Fragment za abbreviated whheels
        AssetManager assetManager = getAssets();
        String[] files = new String[0];
        try {
            files = assetManager.list("wheels");
            for( int idx = 0; idx < files.length;idx++){
                Log.w(TAG, files[idx]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
