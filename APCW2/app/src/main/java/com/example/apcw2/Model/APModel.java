package com.example.apcw2.Model;

import android.graphics.Bitmap;

public class APModel {

    private int ID;
    private String Name;
    private String Date;
    private String Email;
    private Bitmap accountImage;

    public APModel(){}
    public APModel(int ID, String Name, String Date, String Email, Bitmap accountImage){
        this.ID = ID;
        this.Name = Name;
        this.Date = Date;
        this.Email = Email;
        this.accountImage = accountImage;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "ID=" + ID +
                "Name=" + Name + '\'' +
                ", Date='" + Date + '\'' +
                ", Email='" + Email + '\'' +
                ", accountImage='" + accountImage + '\'' +
                '}';
    }

    public String getName(){
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
    public String getDate(){
        return Date;
    }
    public void setDate(String Date){
        this.Date = Date;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }
    public Bitmap getAccountImage() {
        return accountImage;
    }
    public void setAccountImage(Bitmap accountImage) {
        this.accountImage = accountImage;
    }
}
