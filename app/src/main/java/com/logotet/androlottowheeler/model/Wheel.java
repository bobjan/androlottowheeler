package com.logotet.androlottowheeler.model;

import com.logotet.util.MainConverter;

/**
 * Skraceni sistem, samo informacija o njemu, a ne i razrada
 */
public class Wheel {
    private int selectionSize;
    private int garancija;
    private int odIzvucenih;
    private int brojKombinacija;
    private int numbersDrawn;  // 5,6 ili 7
    private boolean fullWheel;

    public Wheel(int size, int garancija, int odIzvucenih, int brojKombinacija, int vrstaLotoa) {
        this.selectionSize = size;
        this.garancija = garancija;
        this.odIzvucenih = odIzvucenih;
        this.brojKombinacija = brojKombinacija;
        this.numbersDrawn = vrstaLotoa;
        fullWheel = false;
    }

    public Wheel(int selectionSize, int vrstaLotoa) {
        this.selectionSize = selectionSize;
        this.numbersDrawn = vrstaLotoa;
        this.garancija = numbersDrawn;
        this.odIzvucenih = numbersDrawn;
        this.brojKombinacija = Kombinacija.calculateKomb(selectionSize, numbersDrawn);
        fullWheel = true;
    }

    public int getSelectionSize() {
        return selectionSize;
    }

    public int getGarancija() {
        return garancija;
    }

    public int getOdIzvucenih() {
        return odIzvucenih;
    }

    public int getBrojKombinacija() {
        return brojKombinacija;
    }

    public int getNumbersDrawn() {
        return numbersDrawn;
    }

    public String getFileName() {
        return MainConverter.intToString(selectionSize, 2) +
                MainConverter.intToString(garancija, 1) +
                MainConverter.intToString(odIzvucenih, 1) +
                MainConverter.intToString(brojKombinacija, 4);
    }

    public String getFileType() {
        switch (numbersDrawn) {
            case 5:
                return "LS5";
            case 7:
                return "LS7";
            default:
                return "LS6";
        }
    }

    public String getFullFileName() {
        return getFileName() + "." + getFileType();
    }

}
