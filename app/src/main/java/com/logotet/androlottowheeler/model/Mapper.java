package com.logotet.androlottowheeler.model;

import java.util.Iterator;

/**
 * Mapper
 * Created by : ?
 * Date: Apr 22, 2005
 */
public class Mapper {
    private Sistem bazniSistem;
    private Sistem resultSistem;

    public Mapper(Sistem bazniSistem) {
        this.bazniSistem = bazniSistem;
        resultSistem = new Sistem(bazniSistem.getName());
    }

    public Sistem getResultSistem(int[] izbor) {
        resultSistem.appendName(naniziBrojeve(izbor));
        Iterator enm = bazniSistem.getKombinacije().iterator();
        while (enm.hasNext()) {
            Kombinacija komb = (Kombinacija) enm.next();
            resultSistem.add(newKomb(izbor, komb));
        }
        return resultSistem;
    }

    private String naniziBrojeve(int[] izbor) {
        String s = "";
        for (int i = 0; i < izbor.length; i++) {
            s += izbor[i] + "-";
        }
        return s.substring(0, s.length() - 1);
    }

    private Kombinacija newKomb(int[] izbor, Kombinacija bookKomb) {
        int[] book = bookKomb.getKomb();
        int[] nova = new int[book.length];
        for (int i = 0; i < book.length; i++) {
            int bookNum = book[i];
            nova[i] = izbor[bookNum - 1];
        }
        return new Kombinacija(nova);
    }

    public static void main(String[] args) {
        int[] oldKomb = {1, 4, 8, 7, 9, 10, 12, 15};
        int[] izbor = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38};
        Sistem oldSistem = new Sistem();
        oldSistem.add(oldKomb);
        Mapper mapper = new Mapper(oldSistem);
        Sistem noviSistem = mapper.getResultSistem(izbor);
        System.out.println(noviSistem);
    }
}
