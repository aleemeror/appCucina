package com.example.studente.appcucinaproject.Cards;

import android.media.Image;

/**
 * Created by Mattia on 22/04/2016.
 */
public class RicettaDetails {

    private String titleRicetta;
    private String descrRicetta;
    private int imgRicettaID;

    public RicettaDetails(int imgRicetta,String title) {
        this.setTitle(title);
        this.setImageRicetta(imgRicetta);
    }

    public String getTitle() {
        return titleRicetta;
    }

    public void setTitle(String msg) {
        this.titleRicetta = msg;
    }
    
    public int getImageRicetta(){
        return imgRicettaID;
    }

    public void setImageRicetta(int imgID){
        this.imgRicettaID = imgID;
    }

}