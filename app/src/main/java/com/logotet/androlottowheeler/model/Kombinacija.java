package com.logotet.androlottowheeler.model;

import java.util.StringTokenizer;

/**
 *
 */
public class Kombinacija {
    private int[] komb;
    private int duzina;
    private int fullSize;

    public Kombinacija(int[] komb) {
        this.komb = komb;
        duzina = komb.length;
        fullSize = makeFullSize();
    }

    public int getDuzina() {
        return duzina;
    }

    public int getFullSize() {
        return fullSize;
    }

    private int makeFullSize() {
        return  calculateKomb(duzina,Constants.numbersPlayed) ;
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
        int brojBrojeva = Constants.numbersInSystem;
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

    public static void main(String[] args) throws IllegalKombinacijaException {
        Constants.numbersInSystem = 39;
        Constants.numbersPlayed = 4;
        System.out.println("8 nad 4 =" + Kombinacija.calculateKomb(8, 4));
        System.out.println("8 nad 5 =" + Kombinacija.calculateKomb(8, 5));
        System.out.println("10 nad 4 =" + Kombinacija.calculateKomb(10, 4));
        System.out.println("6 nad 4 =" + Kombinacija.calculateKomb(6, 4));
        System.out.println("39 nad 3 =" + Kombinacija.calculateKomb(39, 3));
        System.out.println("8 nad 7 =" + Kombinacija.calculateKomb(8, 7));
        System.out.println("28 nad 26 =" + Kombinacija.calculateKomb(28, 26));
        int[] tst = Kombinacija.parseString("2-4-5-7-11-12-15-16-20-33");
        for (int i = 0; i < tst.length; i++) {
            System.out.println((i+1) + ". " + tst[i]);

        }
        Kombinacija kk = new Kombinacija(tst);
        System.out.println("Full size " + kk.getFullSize());
    }


}
