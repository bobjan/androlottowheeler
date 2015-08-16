package com.logotet.androlottowheeler.model;

/**
 * Created by boban on 8/16/15.
 */
public class FullWheel {
    private int base; // broj koliko se izvlaci
    private int size;
    private int brojKombinacija;

    public FullWheel(int size, int base) {
        this.base = base;
        this.size = size;
        this.brojKombinacija = Kombinacija.calculateKomb(size, base);
    }

    public int getSize() {
        return size;
    }

    public int getBrojKombinacija() {
        return brojKombinacija;
    }
}
