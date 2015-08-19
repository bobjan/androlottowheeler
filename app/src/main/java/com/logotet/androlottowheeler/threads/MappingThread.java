package com.logotet.androlottowheeler.threads;

import android.content.res.AssetManager;
import android.util.Log;

import com.logotet.androlottowheeler.model.AllStatic;
import com.logotet.androlottowheeler.model.FullGenerator;
import com.logotet.androlottowheeler.model.IllegalKombinacijaException;
import com.logotet.androlottowheeler.model.Mapper;
import com.logotet.androlottowheeler.model.Sistem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by boban on 8/18/15.
 */
public class MappingThread extends Thread {
    private static final String TAG = "MappingThread";

    AssetManager assetManager;
    private String folder;
    private BufferedReader inFile;
    private Sistem sistem;

    public MappingThread(AssetManager assetManager) {
        super();
        this.assetManager = assetManager;
    }

    @Override
    public void run() {
        if (AllStatic.fullWheel)
            prepareFullWheels();
        else
            prepareAbrWheels();

        Mapper mapper = new Mapper(sistem);
        AllStatic.mappedSistem = mapper.getResultSistem(AllStatic.getPickedArray());
    }

    private void prepareAbrWheels() {
//        Log.w(TAG, "Lotto size = " + AllStatic.numbersDrawn);
        switch (AllStatic.numbersDrawn) {
            case 5:
                folder = "wheels/fives/";
                break;
            case 7:
                folder = "wheels/sevens/";
                break;
            default:
                folder = "wheels/sixes/";
                break;
        }
        try {
            InputStream is = assetManager.open(folder + AllStatic.selectedWheel.getFullFileName());
            inFile = new BufferedReader(new InputStreamReader(is));
            sistem = new Sistem();
            makeSistem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void makeSistem() {
        String linija = null;
        try {
            linija = inFile.readLine();
        } catch (IOException e) {

        }
        if (linija == null)
            return;

//        if (AllStatic.lottoSize == 7) {
//            if (!linija.equals(bazaSistemName))
//                return;
//        }

        if (AllStatic.lottoSize == 6) {              // razraditi proveru ispravnosti fajla !?
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
                switch (AllStatic.numbersDrawn) {
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
//                Log.w(TAG, linija);
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


    private void prepareFullWheels() {
        FullGenerator fg = new FullGenerator(AllStatic.selectedWheel.getSelectionSize(), AllStatic.selectedWheel.getNumbersDrawn());
        sistem = fg.getFullSistem();

    }
}
