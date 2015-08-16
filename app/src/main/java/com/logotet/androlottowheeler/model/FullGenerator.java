package com.logotet.androlottowheeler.model;

/**
 * Created by boban on 8/16/15.
 */
public class FullGenerator {
    private Sistem fullSistem;
    private int vrstaLotoa; // 5,6, ili 7

    public FullGenerator(int size, int base) {
        fullSistem = new Sistem();
        vrstaLotoa = base;

        int[] komb = new int[base];
        int[] max = new int[base];
        for (int i = 0; i < max.length; i++) {
            max[i] = size - base + i;
            komb[i] = i;
        }

        int pivot = base -1;
        pivot = base -1;
        while(pivot >= 0){
            dodaj(komb);
            while(komb[pivot] < max[pivot]){
                komb[pivot]++;
                dodaj(komb);
            }
            pivot--;
            if(pivot >= 0){
                komb[pivot]++;
                if(komb[pivot] < max[pivot]){
                    for (int i = pivot+1; i < komb.length; i++) {
                        komb[i] = komb[i-1]+1;
                    }
                    pivot = base - 1;
                }
            }
        }
    }

    private void dodaj(int[] niz) {
        int[] tmp = new int[niz.length];

        for (int i = 0; i < niz.length; i++) {
            tmp[i] = niz[i] + 1; // generisane kombinacije su 0...(n-1); a ovde se konvertuju u 1 ...n
        }
        fullSistem.add(tmp);
    }
    public Sistem getFullSistem() {
        return fullSistem;
    }

}
