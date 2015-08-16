package com.logotet.androlottowheeler.model;

import java.util.StringTokenizer;

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


    public static int[] parseString(String linija) throws IllegalKombinacijaException {
        StringTokenizer tt = new StringTokenizer(linija, "\t\n\r\f,.;- ");
        int brojBrojeva = AllStatic.lottoSize;
        String[] broj = new String[tt.countTokens()];
        int idx = 0;
        while (tt.hasMoreTokens()) {
            broj[idx++] = tt.nextToken();
        }
        int[] kombTmp = new int[idx];
        for (int i = 0; i < idx; i++) {
            try {
                kombTmp[i] = Integer.parseInt(broj[i]);
                if ((kombTmp[i] > brojBrojeva) || (kombTmp[i] == 0))
                    throw new IllegalKombinacijaException();
            } catch (NumberFormatException nfe) {
                throw new IllegalKombinacijaException();
            }
        }
        return kombTmp;
    }
}
