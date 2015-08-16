package com.logotet.androlottowheeler.threads;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.FullWheel;

/**
 * Created by boban on 8/16/15.
 */
public class FullWheelMaker extends Thread {

    @Override
    public void run() {
        FullWheel fw;
        AllStatic.fullSystems.clear();
        for (int i = AllStatic.numbersDrawn + 1; i < 21; i++) {
                fw = new FullWheel(i, AllStatic.numbersDrawn);
                AllStatic.fullSystems.add(fw);
        }
    }
}
