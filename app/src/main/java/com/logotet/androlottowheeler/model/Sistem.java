package com.logotet.androlottowheeler.model;

import com.logotet.androlottowheeler.model.Kombinacija;

import java.util.ArrayList;


/**
 * Sistem
 * Created by : ?
 * Date: Apr 20, 2005
 */
public class Sistem {
    private static int sistemCounter;
    private ArrayList kombinacije;
    private ArrayList gomila = null;
    private String name = null;
    private int fullSize;
    private int maxColumn;
    private int uniqueId;
    private boolean test;

    public static void setSistemCounter(int tmp) {
        sistemCounter = ++tmp;
    }

    public static void incrementSistemCounter() {
        sistemCounter++;
    }

    public Sistem(String id) {
        this(id, false);
    }

    public Sistem(String id, boolean test) {
        this.test = test;
        uniqueId = sistemCounter;
        if (id == null)
            id = "Test:";
        this.name = id;
        kombinacije = new ArrayList();
        gomila = new ArrayList();
        fullSize = 0;
        maxColumn = 0;
    }

    public Sistem() {
        this("Test:", true);
    }

    public void add(int[] broj) {
        Kombinacija k = new Kombinacija(broj);
        kombinacije.add(k);
        gomila = new ArrayList();
        if (maxColumn < k.getDuzina())
            maxColumn = k.getDuzina();
        fullSize += k.getFullSize();
    }

    public void add(Kombinacija k) {
        kombinacije.add(k);
        gomila = new ArrayList();
        fullSize += k.getFullSize();
        if (maxColumn < k.getDuzina())
            maxColumn = k.getDuzina();
    }

    public String appendName(String dodatak) {
        name += dodatak;
        return name;
    }


    public void setTest(boolean test) {
        this.test = test;
    }

    public ArrayList getKombinacije() {
        return kombinacije;
    }

    public int getBrojKombinacija() {
        return kombinacije.size();
    }


    public int getFullSize() {
        return fullSize;
    }

    public String getName() {
        return name;
    }


    public boolean isTest() {
        return test;
    }

    @Override
    public String toString() {
        return getName();
    }
}
