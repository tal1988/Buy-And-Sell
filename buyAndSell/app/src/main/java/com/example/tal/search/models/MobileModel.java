package com.example.tal.search.models;

import android.graphics.Bitmap;

public class MobileModel
{
    public String mani,modle,memory,saleArea,contact,price,mobileColor;
    public Bitmap imgOne,imgTwo,imgThere;

    public MobileModel(){}

    public MobileModel(String mani, String modle, String memory, String saleArea, String contact
                        , String price, String mobileColor, Bitmap imgOne, Bitmap imgTwo, Bitmap imgThere)
    {
        this.mani = mani;
        this.modle = modle;
        this.memory = memory;
        this.saleArea = saleArea;
        this.contact = contact;
        this.price = price;
        this.mobileColor = mobileColor;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThere = imgThere;
    }

    public String getMani(){return this.mani;}
    public void setMani(String newMani){this.mani = newMani;}

    public String getModle(){return this.modle;}
    public void setModle(String newModle){this.modle = newModle;}

    public String getMemory(){return this.memory;}
    public void setMemory(String newMemory){this.memory = newMemory;}

    public String getSaleArea(){return this.saleArea;}
    public void setSaleArea(String newSaleArea){this.saleArea = newSaleArea;}

    public String getContact(){return this.contact;}
    public void setContact(String newContact){this.contact = newContact;}

    public String getPrice(){return this.price;}
    public void setPrice(String newPrice){this.price = newPrice;}

    public String getMobileColor(){return this.mobileColor;}
    public void setMobileColor(String newColor){this.mobileColor = newColor;}

    public Bitmap getImgOne(){return this.imgOne;}
    public void setImgOne(Bitmap img){this.imgOne = img;}

    public Bitmap getImgTwo(){return this.imgTwo;}
    public void setImgTwo(Bitmap img){this.imgTwo = img;}

    public Bitmap getImgThere(){return this.imgThere;}
    public void setImgThere(Bitmap img){this.imgThere = img;}
}
