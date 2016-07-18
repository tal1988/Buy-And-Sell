package com.example.tal.search.models;

import android.graphics.Bitmap;

public class CarModel
{
    public String mani,modle,engineVol,year,ownersNum,price,engineType,saleArea,contact,carColor;
    public Bitmap imgOne,imgTwo,imgThere;

    public CarModel(){}

    public CarModel(String mani, String modle, String engineVol, String year, String ownersNum
                    , String price, String engineType, String saleArea, String contact, String carColor
                    , Bitmap imgOne, Bitmap imgTwo, Bitmap imgThere)
    {
        this.mani = mani;
        this.modle = modle;
        this.engineVol = engineVol;
        this.year = year;
        this.ownersNum = ownersNum;
        this.price = price;
        this.engineType = engineType;
        this.saleArea = saleArea;
        this.contact = contact;
        this.carColor = carColor;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThere = imgThere;
    }

    public String getMani(){return this.mani;}
    public void setMani(String newMani){this.mani = newMani;}

    public String getModle(){return this.modle;}
    public void setModle(String newModle){this.modle = newModle;}

    public String getEngineVol(){return this.engineVol;}
    public void  setEngineVol(String newEngineVol){this.engineVol = newEngineVol;}

    public String getYear(){return this.year;}
    public void setYear(String newYear){this.year = newYear;}

    public String getOwnersNum(){return this.ownersNum;}
    public void setOwnersNum(String newOwnersNum){this.ownersNum = newOwnersNum;}

    public String getPrice(){return this.price;}
    public void setPrice(String newPrice){this.price = newPrice;}

    public String getEngineType(){return this.engineType;}
    public void setEngineType(String newEngineType){this.engineType = newEngineType;}

    public String getSaleArea(){return this.saleArea;}
    public void setSaleArea(String newSaleArea){this.saleArea = newSaleArea;}

    public String getContact(){return this.contact;}
    public void setContact(String newContact){this.contact = newContact;}

    public String getCarColor(){return this.carColor;}
    public void setCarColor(String newCarColor){this.carColor = newCarColor;}

    public Bitmap getImgOne(){return this.imgOne;}
    public void setImgOne(Bitmap img){this.imgOne = img;}

    public Bitmap getImgTwo(){return this.imgTwo;}
    public void setImgTwo(Bitmap img){this.imgTwo = img;}

    public Bitmap getImgThere(){return this.imgThere;}
    public void setImgThere(Bitmap img){this.imgThere = img;}
}
