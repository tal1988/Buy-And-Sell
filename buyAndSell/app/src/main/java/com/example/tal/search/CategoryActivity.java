package com.example.tal.search;

//Class imports.
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.example.tal.search.models.*;
import com.parse.ParseObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Opening CategortyActivity activity.
public class CategoryActivity extends Activity
{
    //Class variables.
    public final int FIRST = 0;
    public final int SECOUND = 1;
    public final int THERD = 2;
    public final int MOBILE = 1;
    public final int COMPUTER = 2;
    public final int FURNITURE = 3;
    public final int CAR = 4;
    public final int APARTMENT = 5;

    public static List<ParseObject> objectList;
    public static HashMap<String,List<ParseObject>> objectsImgs;

    public int category,i;
    public String imgOneString,imgTwoString,imgThereString;
    public TextView mani,modle,maniText,modleText;

    protected List<MobileModel> mobiles;
    protected List<ComputerModel> computers;
    protected List<FurnitureModel> furniture;
    protected List<CarModel> cars;
    protected List<ApartmentModel> apartments;

    private CustomListAdapter customListAdapter;
    private ListView listView;
    private ParseHandler parseHandler;

    //Method that executed when the activity create.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView)findViewById(R.id.categoryList);
        mani = (TextView)findViewById(R.id.text1);
        modle = (TextView)findViewById(R.id.text2);
        maniText = (TextView)findViewById(R.id.text1Text);
        modleText = (TextView)findViewById(R.id.text2Text);
        category = (int)getIntent().getExtras().get("category");
        parseHandler = new ParseHandler();
        convertHandler(category);
    }

    //Method that handle all the converts from pars objects that we got from data base to our models.
    public void convertHandler(int categoryNumber)
    {
        if(categoryNumber == MOBILE)
        {
            mobiles = new ArrayList<MobileModel>();
            customListAdapter = new CustomListAdapter(this,mobiles,MOBILE);
            listView.setAdapter(customListAdapter);
            convertParsObjectsToMobile(objectList);
        }
        else if(categoryNumber == COMPUTER)
        {
            computers = new ArrayList<ComputerModel>();
            customListAdapter = new CustomListAdapter(this,computers,COMPUTER);
            listView.setAdapter(customListAdapter);
            convertParsObjectsToComputer(objectList);
        }
        else if(categoryNumber == FURNITURE)
        {
            furniture = new ArrayList<FurnitureModel>();
            customListAdapter = new CustomListAdapter(this,furniture,FURNITURE);
            listView.setAdapter(customListAdapter);
            convertParsObjectsToFurniture(objectList);
        }
        else if(categoryNumber == CAR)
        {
            cars = new ArrayList<CarModel>();
            customListAdapter = new CustomListAdapter(this,cars,CAR);
            listView.setAdapter(customListAdapter);
            convertParsObjectsToCar(objectList);
        }
        else
        {
            apartments = new ArrayList<ApartmentModel>();
            customListAdapter = new CustomListAdapter(this,apartments,APARTMENT);
            listView.setAdapter(customListAdapter);
            convertParsObjectsToAparment(objectList);
        }
    }

    //Method that actually convert pars objects to mobile model.
    public void convertParsObjectsToMobile(List<ParseObject> list)
    {
        try
        {
            for(i = 0 ; i < list.size() ; i++)
            {
                imgOneString = imgTwoString = imgThereString = null;
                MobileModel mobileModel = new MobileModel();
                Log.d("phone mani is:",""+list.get(i).get("Manufacturer").toString());
                mobileModel.setMani(list.get(i).get("Manufacturer").toString());
                mobileModel.setModle(list.get(i).get("Modle").toString());
                mobileModel.setMemory(list.get(i).get("Memory").toString());
                mobileModel.setSaleArea(list.get(i).get("SaleArea").toString());
                mobileModel.setContact(list.get(i).get("Contact").toString());
                mobileModel.setPrice(list.get(i).get("Price").toString());
                mobileModel.setMobileColor(list.get(i).get("Color").toString());
                List<ParseObject> imgs =  new ArrayList<>();
                imgs = objectsImgs.get(list.get(i).getObjectId().toString());
                if(imgs.get(FIRST).get("image") != null)
                    imgOneString = imgs.get(FIRST).get("image").toString();
                if(imgs.get(SECOUND).get("image") != null)
                    imgTwoString = imgs.get(SECOUND).get("image").toString();
                if(imgs.get(THERD).get("image") != null)
                    imgThereString = imgs.get(THERD).get("image").toString();

                if(imgOneString != null)
                    mobileModel.setImgOne(stringToBitMap(imgOneString));
                if(imgTwoString != null)
                    mobileModel.setImgTwo(stringToBitMap(imgTwoString));
                if(imgThereString != null)
                    mobileModel.setImgThere(stringToBitMap(imgThereString));

                mobiles.add(mobileModel);
            }
            customListAdapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    //Method that actually convert pars objects to computer model.
    public void convertParsObjectsToComputer(List<ParseObject> list)
    {
        try
        {
            for(i = 0 ; i < list.size() ; i++)
            {
                imgOneString = imgTwoString = imgThereString = null;
                ComputerModel computerModel = new ComputerModel();
                computerModel.setMani(list.get(i).get("Manufacturer").toString());
                computerModel.setModle(list.get(i).get("Modle").toString());
                computerModel.setRam(list.get(i).get("RAM").toString());
                computerModel.setDisk(list.get(i).get("HardDisk").toString());
                computerModel.setProcessor(list.get(i).get("Processor").toString());
                computerModel.setCompColor(list.get(i).get("Color").toString());
                computerModel.setPrice(list.get(i).get("Price").toString());
                computerModel.setContact(list.get(i).get("Contact").toString());
                computerModel.setSaleArea(list.get(i).get("SaleArea").toString());
                List<ParseObject> imgs =  new ArrayList<>();
                imgs = objectsImgs.get(list.get(i).getObjectId().toString());
                if(imgs.get(FIRST).get("image") != null)
                    imgOneString = imgs.get(FIRST).get("image").toString();
                if(imgs.get(SECOUND).get("image") != null)
                    imgTwoString = imgs.get(SECOUND).get("image").toString();
                if(imgs.get(THERD).get("image") != null)
                    imgThereString = imgs.get(THERD).get("image").toString();

                if(imgOneString != null)
                    computerModel.setImgOne(stringToBitMap(imgOneString));
                else
                    computerModel.setImgOne(null);
                if(imgTwoString != null)
                    computerModel.setImgTwo(stringToBitMap(imgTwoString));
                else
                    computerModel.setImgTwo(null);
                if(imgThereString != null)
                    computerModel.setImgThere(stringToBitMap(imgThereString));
                else
                    computerModel.setImgThere(null);

                computers.add(computerModel);
            }
            customListAdapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    //Method that actually convert pars objects to furniture model.
    public void convertParsObjectsToFurniture(List<ParseObject> list)
    {
        try
        {
            for(i = 0 ; i < list.size() ; i++)
            {
                imgOneString = imgTwoString = imgThereString = null;
                FurnitureModel furnitureModel = new FurnitureModel();
                furnitureModel.setType(list.get(i).get("Type").toString());
                furnitureModel.setUnGroup(list.get(i).get("Ungroup").toString());
                furnitureModel.setFurnColor(list.get(i).get("Color").toString());
                furnitureModel.setSize(list.get(i).get("Size").toString());
                furnitureModel.setSaleArea(list.get(i).get("SaleArea").toString());
                furnitureModel.setPrice(list.get(i).get("Price").toString());
                furnitureModel.setContact(list.get(i).get("Contact").toString());
                List<ParseObject> imgs =  new ArrayList<>();
                imgs = objectsImgs.get(list.get(i).getObjectId().toString());
                if(imgs.get(FIRST).get("image") != null)
                    imgOneString = imgs.get(FIRST).get("image").toString();
                if(imgs.get(SECOUND).get("image") != null)
                    imgTwoString = imgs.get(SECOUND).get("image").toString();
                if(imgs.get(THERD).get("image") != null)
                    imgThereString = imgs.get(THERD).get("image").toString();

                if(imgOneString != null)
                    furnitureModel.setImgOne(stringToBitMap(imgOneString));
                if(imgTwoString != null)
                    furnitureModel.setImgTwo(stringToBitMap(imgTwoString));
                if(imgThereString != null)
                    furnitureModel.setImgThere(stringToBitMap(imgThereString));

                furniture.add(furnitureModel);
            }
            customListAdapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    //Method that actually convert pars objects to car model.
    public void convertParsObjectsToCar(List<ParseObject> list)
    {
        try
        {
            for(i = 0 ; i < list.size() ; i++)
            {
                imgOneString = imgTwoString = imgThereString = null;
                CarModel carModel = new CarModel();
                carModel.setMani(list.get(i).get("Manufacturer").toString());
                carModel.setModle(list.get(i).get("Modle").toString());
                carModel.setEngineVol(list.get(i).get("EngineVolume").toString());
                carModel.setYear(list.get(i).get("Year").toString());
                carModel.setOwnersNum(list.get(i).get("OwnerNumber").toString());
                carModel.setPrice(list.get(i).get("Price").toString());
                carModel.setEngineType(list.get(i).get("EngineType").toString());
                carModel.setSaleArea(list.get(i).get("SaleArea").toString());
                carModel.setContact(list.get(i).get("Contact").toString());
                carModel.setCarColor(list.get(i).get("Color").toString());
                List<ParseObject> imgs =  new ArrayList<>();
                imgs = objectsImgs.get(list.get(i).getObjectId().toString());
                if(imgs.get(FIRST).get("image") != null)
                    imgOneString = imgs.get(FIRST).get("image").toString();
                if(imgs.get(SECOUND).get("image") != null)
                    imgTwoString = imgs.get(SECOUND).get("image").toString();
                if(imgs.get(THERD).get("image") != null)
                    imgThereString = imgs.get(THERD).get("image").toString();

                if(imgOneString != null)
                    carModel.setImgOne(stringToBitMap(imgOneString));
                if(imgTwoString != null)
                    carModel.setImgTwo(stringToBitMap(imgTwoString));
                if(imgThereString != null)
                    carModel.setImgThere(stringToBitMap(imgThereString));

                cars.add(carModel);
            }
            customListAdapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    //Method that actually convert pars objects to apartment model.
    public void convertParsObjectsToAparment(List<ParseObject> list)
    {
        try
        {
            for(i = 0 ; i < list.size() ; i++)
            {
                imgOneString = imgTwoString = imgThereString = null;
                ApartmentModel apartmentModel = new ApartmentModel();
                apartmentModel.setType(list.get(i).get("Type").toString());
                apartmentModel.setCity(list.get(i).get("City").toString());
                apartmentModel.setNeigh(list.get(i).get("Neighborhood").toString());
                apartmentModel.setAddress(list.get(i).get("Address").toString());
                apartmentModel.setRooms(list.get(i).get("RoomAmount").toString());
                apartmentModel.setBalconies(list.get(i).get("BalconiesAmount").toString());
                apartmentModel.setFloor(list.get(i).get("FloorNumber").toString());
                apartmentModel.setSize(list.get(i).get("Size").toString());
                apartmentModel.setContact(list.get(i).get("Contact").toString());
                apartmentModel.setPrice(list.get(i).get("Price").toString());

                List<ParseObject> imgs =  new ArrayList<>();
                imgs = objectsImgs.get(list.get(i).getObjectId().toString());
                if(imgs.get(FIRST).get("image") != null)
                    imgOneString = imgs.get(FIRST).get("image").toString();
                if(imgs.get(SECOUND).get("image") != null)
                    imgTwoString = imgs.get(SECOUND).get("image").toString();
                if(imgs.get(THERD).get("image") != null)
                    imgThereString = imgs.get(THERD).get("image").toString();

                if(imgOneString != null)
                    apartmentModel.setImgOne(stringToBitMap(imgOneString));
                if(imgTwoString != null)
                    apartmentModel.setImgTwo(stringToBitMap(imgTwoString));
                if(imgThereString != null)
                    apartmentModel.setImgThere(stringToBitMap(imgThereString));

                apartments.add(apartmentModel);
            }
            customListAdapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){e.printStackTrace();}
    }

    //Method that convert string to bitmap.
    @TargetApi(8)
    public Bitmap stringToBitMap(String encodedString)
    {
        try
        {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e)
        {
            e.getMessage();
            return null;
        }
    }

    //Get methods for all the models lists we have.
    public List<MobileModel> getMobiles(){return this.mobiles;}
    public List<ComputerModel> getComputers(){return this.computers;}
    public List<FurnitureModel> getFurniture(){return this.furniture;}
    public List<CarModel> getCars(){return this.cars;}
    public List<ApartmentModel> getApartments(){return this.apartments;}

    //Method that finish the current acitivty.
    public void onReturnButtonPressedInList(View view)
    {
        finish();
    }
}