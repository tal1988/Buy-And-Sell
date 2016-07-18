package com.example.tal.search.models;

import android.graphics.Bitmap;

public class ApartmentModel
{
    public String type,city,neigh,address,rooms,balconies,floor,size,contact,price;
    public Bitmap imgOne,imgTwo,imgThere;

    public ApartmentModel(){}

    public ApartmentModel(String type, String city, String neigh, String address, String rooms, String balconies
                        , String floor, String size, String contact, String price
                        , Bitmap imgOne, Bitmap imgTwo, Bitmap imgThere)
    {
        this.type = type;
        this.city = city;
        this.neigh = neigh;
        this.address = address;
        this.rooms = rooms;
        this.balconies = balconies;
        this.floor = floor;
        this.size = size;
        this.contact = contact;
        this.price = price;
        this.imgOne = imgOne;
        this.imgTwo = imgTwo;
        this.imgThere = imgThere;
    }

    public String getType(){return this.type;}
    public void setType(String newType){this.type = newType;}

    public String getCity(){return this.city;}
    public void setCity(String newCity){this.city = newCity;}

    public String getNeigh(){return this.neigh;}
    public void setNeigh(String newNeigh){this.neigh = newNeigh;}

    public String getAddress(){return this.address;}
    public void setAddress(String newAddress){this.address = newAddress;}

    public String getRooms(){return this.rooms;}
    public void setRooms(String newRooms){this.rooms = newRooms;}

    public String getBalconies(){return this.balconies;}
    public void setBalconies(String newBalconies){this.balconies = newBalconies;}

    public String getFloor(){return this.floor;}
    public void setFloor(String newFloor){this.floor = newFloor;}

    public String getSize(){return this.size;}
    public void setSize(String newSize){this.size = newSize;}

    public String getContact(){return this.contact;}
    public void setContact(String newContact){this.contact = newContact;}

    public String getPrice(){return this.price;}
    public void setPrice(String newPrice){this.price = newPrice;}

    public Bitmap getImgOne(){return this.imgOne;}
    public void setImgOne(Bitmap img){this.imgOne = img;}

    public Bitmap getImgTwo(){return this.imgTwo;}
    public void setImgTwo(Bitmap img){this.imgTwo = img;}

    public Bitmap getImgThere(){return this.imgThere;}
    public void setImgThere(Bitmap img){this.imgThere = img;}
}
