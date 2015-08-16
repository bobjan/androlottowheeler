package com.logotet.androlottowheeler.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by boban on 8/2/15.
 */
public class AllStatic {
    public static ArrayList<MyNumberCell> tiket = new ArrayList();

    public static final String MY_PREFS_NAME = "LottoWheelsPrefs";

    public static int width;
    public static int height;
    public static int pixelWidth;
    public static int pixelHeight;

    public static int pickedCounter;  // for display purpose only; treba da bude jednak systemSize da bi se pristupilo generisanju koinacija
    public static String pickedText; // for display purpose only;
   public static int systemSize; // broj brojeva koji se koristi za generisanje kombinacija; veci od gameSize!!

// iz stare aplikacije Constants.
    public static String rootFolder = "wheels";


    // to be in SharedPreferences
    public  static int numbersDrawn;   // koliko se brojeva izvlaci // 5, 6 ili 7 == vrstaLotoa == lottoType
    public static  int lottoSize;    // 39,  49 ili ...
    public static String email;
    public static boolean fullWheel;




    public static ArrayList<FullWheel> fullSystems = new ArrayList();
    public static ArrayList<Wheel> wheelSystems = new ArrayList();


    public static Sistem selectedSistem;
    public static Sistem mappedSistem;



    public static Wheel selectedWheel;

    public static void recalculate(){
        int brojac = 0;
        Iterator it = tiket.iterator();
        StringBuffer sb = new StringBuffer();
        String dash = "";
        while(it.hasNext()){
            MyNumberCell myButton = (MyNumberCell) it.next();
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
            MyNumberCell myButton = (MyNumberCell) it.next();
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
            MyNumberCell myButton = (MyNumberCell) it.next();
            if(myButton.isPicked())
                niz[idx++] = myButton.getValue();
        }
        return niz;
    }
}
