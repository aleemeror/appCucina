package com.example.studente.appcucinaproject.Spesa;

public class SpesaObject {

    private String titleSpesa;


    public SpesaObject(String title) {
        this.setTitle(title);
    }

    public String getTitle() {
        return titleSpesa;
    }

    public void setTitle(String msg) {
        this.titleSpesa = msg;
    }
}