package com.logotet.androlottowheeler.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by boban on 8/2/15.
 */
public class AllStatic {
    public static ArrayList<MyButton> tiket = new ArrayList();

    public static int width;
    public static int height;
    public static int pixelWidth;
    public static int pixelHeight;

    public static int pickedCounter;
    public static String pickedText;
    public static int gameSize;
    public static int systemSize; // broj brojeva koji se koristi za generisanje kombinacija; veci od gameSize!!
    public static int maxNumbers;


    public static void recalculate(){
        int brojac = 0;
        Iterator it = tiket.iterator();
        StringBuffer sb = new StringBuffer();
        String dash = "";
        while(it.hasNext()){
            MyButton myButton = (MyButton) it.next();
            if(sb.length() != 0)
                dash = "-";
            if(myButton.isPicked()){
                brojac++;
                sb.append(dash);
                sb.append(myButton.getValue() + "");
            }
        }
        pickedText =  sb.toString();
        pickedCounter = brojac;
    }

    public static void clearAll() {
        Iterator it = tiket.iterator();
        while(it.hasNext()) {
            MyButton myButton = (MyButton) it.next();
            myButton.setPicked(false);
        }
        pickedCounter = 0;
        pickedText = "";
    }
    public int[] getPickedArray(){
        int[] niz = new int[systemSize];
        int idx = 0;
        Iterator it = tiket.iterator();
        while(it.hasNext()) {
            MyButton myButton = (MyButton) it.next();
            if(myButton.isPicked())
                niz[idx++] = myButton.getValue();
        }
        return niz;
    }
}
