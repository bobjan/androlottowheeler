package com.logotet.androlottowheeler.model;

import com.logotet.util.MainConverter;

/**
 * Skraceni sistem, samo informacija o njemu, a ne i razrada
 */
public class Wheel {
    private int odigranihBrojeva;
    private int garancija;
    private int odIzvucenih;
    private int brojKombinacija;
    private int vrstaLotoa;

    public Wheel(int odgranihBrojeva, int garancija, int odIzvucenih, int brojKombinacija, int vrstaLotoa) {
        this.odigranihBrojeva = odgranihBrojeva;
        this.garancija = garancija;
        this.odIzvucenih = odIzvucenih;
        this.brojKombinacija = brojKombinacija;
        this.vrstaLotoa = vrstaLotoa;
    }

    public int getOdigranihBrojeva() {
        return odigranihBrojeva;
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

    public int getVrstaLotoa() {
        return vrstaLotoa;
    }

    public String getFileName() {
        return MainConverter.intToString(odigranihBrojeva, 2) +
                MainConverter.intToString(garancija, 1) +
                MainConverter.intToString(odIzvucenih, 1) +
                MainConverter.intToString(brojKombinacija, 4);
    }

    public String getFileType() {
        switch (vrstaLotoa) {
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
