package com.logotet.androlottowheeler.parser;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.IllegalKombinacijaException;
import com.logotet.androlottowheeler.model.Sistem;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Cita trazeni wheel i popunjava klasu Sistem procitanim kombinacijama
 */
public class WheelStreamer {
    private Sistem sistem;
    BufferedReader inFile;
    private String bazaSistemName;
    private int vrstaLotoa;

    public WheelStreamer(String bazaSistemName) throws FileNotFoundException {
        vrstaLotoa = AllStatic.numbersDrawn;
        this.bazaSistemName = bazaSistemName;
        String subfolder = null;
        String filetype;

        switch (vrstaLotoa) {
            case 5:
                subfolder = "/fives";
                filetype = ".LS5";
                break;
            case 7:
                subfolder = "/sevens";
                filetype = ".LS7";
                break;
            default:
                subfolder = "/sixes";
                filetype = ".LS6";
                break;
        }

        String tmp = "wheels" + subfolder + "/" + bazaSistemName + filetype;
        String ttt = this.getClass().getCanonicalName();
        InputStream str = new FileInputStream(new File(tmp));

        if (str == null)
            throw new FileNotFoundException("Not Found " + tmp);
        inFile = new BufferedReader(new InputStreamReader(str));
        sistem = new Sistem();
    }

    private String makeSistemName(String str) {
        String s = str.substring(0, 2) + "-" + str.substring(2, 3) + "-" + str.substring(3, 4) + "-" + str.substring(4);
        return s + "(S):  ";
    }

    public Sistem getSistem() {
        makeSistem();
        return sistem;
    }

    /***
     *  cita ceo fajl; pravi komb i smest au klasu Sistem
     *
     * */
    private void makeSistem() {
        String linija = null;
        try {
            linija = inFile.readLine();
        } catch (IOException e) {

        }
        if (linija == null)
            return;

        if (vrstaLotoa == 7) {
            if (!linija.equals(bazaSistemName))
                return;
        }

        if (vrstaLotoa == 6) {              // razraditi proveru ispravnosti fajla !?
            try {
                linija = inFile.readLine();
                if (linija == null)
                    return;
            } catch (IOException e) {
                return;
            }
        }

        boolean petlja = true;
        while (petlja) {
            try {
                linija = null;
                linija = inFile.readLine();
                int[] komb;
                switch (vrstaLotoa) {
                    case 5:
                        komb = makeFiver(linija);
                        break;
                    case 7:
                        komb = makeSevenKomb(linija);
                        break;
                    default:
                        komb = makeSixer(linija);
                        break;
                }

                sistem.add(komb);
            } catch (NullPointerException e) {
                petlja = false;
            } catch (IllegalKombinacijaException e) {
            } catch (IOException e) {
                petlja = false;
            }
        }

        try {
            inFile.close();
        } catch (IOException e) {
        }
    }

    private int[] makeFiver(String linija) throws IllegalKombinacijaException {
        int duzina = linija.length() / 2;
        if (duzina < 5)
            throw new IllegalKombinacijaException();
        int[] komb = new int[duzina];
        for (int i = 0; i < duzina; i++) {
            int idx = i * 2;
            String broj = linija.substring(idx, idx + 2);
            try {
                komb[i] = Integer.parseInt(broj);
            } catch (NumberFormatException nfe) {
                throw new IllegalKombinacijaException();
            }
        }
        return komb;
    }

    private int[] makeSixer(String linija) throws IllegalKombinacijaException {
        StringTokenizer st = new StringTokenizer(linija, ",");
        int broj = st.countTokens();
        if (broj < 6)
            throw new IllegalKombinacijaException();
        int[] niz = new int[broj];
        int ind = 0;
        while (st.hasMoreTokens()) {
            try {
                niz[ind++] = Integer.parseInt(st.nextToken().trim());
            } catch (NumberFormatException nfe) {
                throw new IllegalKombinacijaException();
            }
        }
        return niz;
    }

    private int[] makeSevenKomb(String linija) throws IllegalKombinacijaException {
        int duzina = linija.length() / 2;
        if (duzina < 7)
            throw new IllegalKombinacijaException();
        int[] komb = new int[duzina];
        for (int i = 0; i < duzina; i++) {
            int idx = i * 2;
            String broj = linija.substring(idx, idx + 2);
            try {
                komb[i] = Integer.parseInt(broj);
            } catch (NumberFormatException nfe) {
                throw new IllegalKombinacijaException();
            }
        }
        return komb;
    }

}