package com.example.android.secrethands.datastructures;

/**
 * Created by Aly on 7/29/2019.
 */

public class Disorders {
    private String name;
    private int iconID;
    private int imgID;
    private int syndromes;
    private int treatments;
    private int description;

    public Disorders() {}

    public Disorders(String name, int iconID, int imgID, int description, int syndromes, int treatments) {
        this.name = name;
        this.iconID = iconID;
        this.imgID = imgID;
        this.syndromes = syndromes;
        this.treatments = treatments;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getTreatments() {
        return treatments;
    }

    public void setTreatments(int treatments) {
        this.treatments = treatments;
    }

    public int getSyndromes() {
        return syndromes;
    }

    public void setSyndromes(int syndromes) {
        this.syndromes = syndromes;
    }

}
