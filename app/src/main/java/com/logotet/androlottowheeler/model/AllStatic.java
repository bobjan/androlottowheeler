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

    public static int pickedCounter;  // for display purpose only; treba da bude jednak systemSize da bi se pristupilo generisanju koinacija
    public static String pickedText; // for display purpose only;
    public static int gameSize;
    public static int systemSize; // broj brojeva koji se koristi za generisanje kombinacija; veci od gameSize!!
    public static int maxNumbers;

// iz stare aplikacije Constants.
    public static String fileNames = "sistem";
    public  static int numbersPlayed;   // koliko se brojeva izvlaci // 5, 6 ili 7 == vrstaLotoa == lottoType
    public static  int numbersInSystem;    // 39,  49 ili ...
    public static ArrayList system = new ArrayList();
    public static Wheel selectedWheel;


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
