package com.logotet.androlottowheeler.model;

/**
 *
 */
public class Kombinacija {
    private int[] komb;

    public Kombinacija(int[] komb) {
        this.komb = komb;
    }

    /**
     * racuna n nad k = n! / k! (n-k)!
     */
    public static int calculateKomb(int n, int k) {
        int up = n;
        int down = 1;
        int tmp = 1;
        while ((up > k) && (down < (k + 1))) {
            tmp *= up--;
            tmp /= down++;
        }
        return tmp;
    }

    public int[] getKomb() {
        return komb;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < komb.length; i++) {
            s += komb[i];
            if (i < komb.length - 1)
                s += "-";
        }
        return s;
    }

}
