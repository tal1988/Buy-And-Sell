package com.example.tal.search.models;


import android.graphics.Bitmap;

public class FurnitureModel
{
    public String type,unGroup,furnColor,size,saleArea,price,contact;
    public Bitmap imgOne,imgTwo,imgThere;

    public FurnitureModel(){}

    public FurnitureModel(String type, String unGroup, String furnColor, String size, String saleArea
                        , String price, String contact, Bitmap imgOne, Bitmap imgTwo, Bitmap imgThere)
    {
        this.type = type;
        this.unGroup = unGroup;
        this.furnColor = furnColor;
        this.size = size;
        this.saleArea = saleArea;
        this.price = price;
        this.contact = contact;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThere = imgThere;
    }

    public String getType(){return this.type;}
    public void setType(String newType){this.type = newType;}

    public String getUnGroup(){return this.unGroup;}
    public void setUnGroup(String newUnGroup){this.unGroup = newUnGroup;}

    public String getFurnColor(){return this.furnColor;}
    public void setFurnColor(String newFurnColor){this.furnColor = newFurnColor;}

    public String getSize(){return this.size;}
    public void setSize(String newSize){this.size = newSize;}

    public String getSaleArea(){return this.saleArea;}
    public void setSaleArea(String newSaleArea){this.saleArea = newSaleArea;}

    public String getPrice(){return this.price;}
    public void setPrice(String newPrice){this.price = newPrice;}

    public String getContact(){return this.contact;}
    public void setContact(String newContact){this.contact = newContact;}

    public Bitmap getImgOne(){return this.imgOne;}
    public void setImgOne(Bitmap img){this.imgOne = img;}

    public Bitmap getImgTwo(){return this.imgTwo;}
    public void setImgTwo(Bitmap img){this.imgTwo = img;}

    public Bitmap getImgThere(){return this.imgThere;}
    public void setImgThere(Bitmap img){this.imgThere = img;}
}
