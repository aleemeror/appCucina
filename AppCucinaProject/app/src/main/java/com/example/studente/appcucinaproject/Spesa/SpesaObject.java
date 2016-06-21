package com.example.studente.appcucinaproject.Spesa;

import android.os.Parcel;
import android.os.Parcelable;

public class SpesaObject implements Parcelable{

    private String titleSpesa;
    private String ingredientiSpesa;


    public SpesaObject(String title, String ingredienti) {
        this.setTitle(title);
        this.setIngredientiSpesa(ingredienti);
    }

    protected SpesaObject(Parcel in) {
        titleSpesa = in.readString();
        ingredientiSpesa = in.readString();
    }



    public String getTitle() {
        return titleSpesa;
    }

    public void setTitle(String msg) {
        this.titleSpesa = msg;
    }

    public String getIngredientiSpesa() {
        return ingredientiSpesa;
    }

    public void setIngredientiSpesa(String ingredientiSpesa) {
        this.ingredientiSpesa = ingredientiSpesa;
    }


    public static final Creator<SpesaObject> CREATOR = new Creator<SpesaObject>() { //metodi per il Parcelable
        @Override
        public SpesaObject createFromParcel(Parcel in) {
            return new SpesaObject(in);
        }

        @Override
        public SpesaObject[] newArray(int size) {
            return new SpesaObject[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleSpesa);
        dest.writeString(ingredientiSpesa);
    }
}