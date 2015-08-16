package com.logotet.androlottowheeler.model;

import com.logotet.androlottowheeler.model.Kombinacija;

import java.util.ArrayList;


/**
 * Sistem
 * Created by : ?
 * Date: Apr 20, 2005
 */
public class Sistem {
    private ArrayList kombinacije;

    public Sistem() {
        kombinacije = new ArrayList();
    }


    public void add(int[] broj) {
        Kombinacija k = new Kombinacija(broj);
        kombinacije.add(k);
    }

    public void add(Kombinacija k) {
        kombinacije.add(k);
    }


    public ArrayList getKombinacije() {
        return kombinacije;
    }

    public int getBrojKombinacija() {
        return kombinacije.size();
    }

}
