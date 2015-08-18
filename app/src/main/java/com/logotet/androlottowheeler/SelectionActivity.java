package com.logotet.androlottowheeler;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.logotet.androlottowheeler.threads.WheelsFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/***
 * proveriti ove skracene sisteme
 * ﹕ 12660408.LS7
 * 13660888.LS7
 * 13670224.LS7
 */

public class SelectionActivity extends ActionBarActivity implements ActionBar.TabListener {
    private static final String TAG = "SelectionActivity";
    private ArrayList allFiles = new ArrayList();

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
            files = assetManager.list("wheels/fives");
            for (int idx = 0; idx < files.length; idx++) {
                allFiles.add(files[idx]);
            }
            files = assetManager.list("wheels/sixes");
            for (int idx = 0; idx < files.length; idx++) {
                allFiles.add(files[idx]);
            }
            files = assetManager.list("wheels/sevens");
            for (int idx = 0; idx < files.length; idx++) {
                allFiles.add(files[idx]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String[] names = WheelsFactory.wheelNames;
        for (int i = 0; i < names.length; i++) {
            Iterator iterator = allFiles.iterator();
            boolean found = false;

            while (iterator.hasNext()) {
                String file = (String) iterator.next();
                if (names[i].equals(file)) {
                    found = true;
                    break;
                }
            }
            if (!found)
                Log.w(TAG, names[i]);
        }
        Log.w(TAG, "DONE!");

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
