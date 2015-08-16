package com.logotet.androlottowheeler.model;

import android.util.Log;

/**
 * Created by boban on 8/2/15.
 */
public class MyNumberCell {
    private static final String TAG = "MyNumberCell";
    private boolean picked;
    private int value;

    public MyNumberCell() {
        picked = false;
        value = 0;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void switchState(){
        picked = picked ? false : true;
    }

}
