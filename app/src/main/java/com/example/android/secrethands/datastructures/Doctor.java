package com.example.android.secrethands.datastructures;

/**
 * Created by Aly on 7/29/2019.
 */

public class Doctor extends User {
    private String nationalId;
    private String photoURL;
    public Doctor() {
    }
    public Doctor(int age, String gender, String username, int type, String nationalId, String photoURL,String ID) {
        super(age, gender, username, type,ID);
        this.nationalId = nationalId;
        this.photoURL = photoURL;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
