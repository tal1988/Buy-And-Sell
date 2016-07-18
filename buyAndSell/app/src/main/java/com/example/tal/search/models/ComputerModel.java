package com.example.tal.search.models;


import android.graphics.Bitmap;

public class ComputerModel
{
    public String mani,modle,ram,disk,processor,compColor,price,contact,saleArea;
    public Bitmap imgOne,imgTwo,imgThere;

    public ComputerModel(){}

    public ComputerModel(String mani, String modle, String ram, String disk, String processor
                        , String compColor, String price, String contact, String saleArea
                        , Bitmap imgOne, Bitmap imgTwo, Bitmap imgThere)
    {
        this.mani = mani;
        this.modle = modle;
        this.ram = ram;
        this.disk = disk;
        this.processor = processor;
        this.compColor = compColor;
        this.price = price;
        this.contact = contact;
        this.saleArea = saleArea;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThere = imgThere;
    }

    public String getMani(){return this.mani;}
    public void setMani(String newMani){this.mani = newMani;}

    public String getModle(){return this.modle;}
    public void setModle(String newModle){this.modle = newModle;}

    public String getRam(){return this.ram;}
    public void setRam(String newRam){this.ram = newRam;}

    public String getDisk(){return this.disk;}
    public void setDisk(String newDisk){this.disk = newDisk;}

    public String getProcessor(){return this.processor;}
    public void setProcessor(String newProcessor){this.processor = newProcessor;}

    public String getCompColor(){return this.compColor;}
    public void setCompColor(String newColor){this.compColor = newColor;}

    public String getPrice(){return this.price;}
    public void setPrice(String newPrice){this.price = newPrice;}

    public String getContact(){return this.contact;}
    public void setContact(String newContact){this.contact = newContact;}

    public String getSaleArea(){return this.saleArea;}
    public void setSaleArea(String newSaleArea){this.saleArea = newSaleArea;}

    public Bitmap getImgOne(){return this.imgOne;}
    public void setImgOne(Bitmap img){this.imgOne = img;}

    public Bitmap getImgTwo(){return this.imgTwo;}
    public void setImgTwo(Bitmap img){this.imgTwo = img;}

    public Bitmap getImgThere(){return this.imgThere;}
    public void setImgThere(Bitmap img){this.imgThere = img;}
}
