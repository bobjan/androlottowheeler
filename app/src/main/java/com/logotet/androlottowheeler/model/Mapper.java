package com.logotet.androlottowheeler.model;

import java.util.Iterator;

/**
 * Mapper
 */
public class Mapper {
    private Sistem bazniSistem;
    private Sistem resultSistem;

    public Mapper(Sistem bazniSistem) {
        this.bazniSistem = bazniSistem;
        resultSistem = new Sistem();
    }

    public Sistem getResultSistem(int[] izbor) {
        Iterator enm = bazniSistem.getKombinacije().iterator();
        while (enm.hasNext()) {
            Kombinacija bookkomb = (Kombinacija) enm.next();
            resultSistem.add(getMapped(izbor, bookkomb));
        }
        return resultSistem;
    }

    private Kombinacija getMapped(int[] izbor, Kombinacija bookKomb) {
        int[] book = bookKomb.getKomb();
        int[] nova = new int[book.length];
        for (int i = 0; i < book.length; i++) {
            int bookNum = book[i];
            nova[i] = izbor[bookNum - 1];
        }
        return new Kombinacija(nova);
    }

}
