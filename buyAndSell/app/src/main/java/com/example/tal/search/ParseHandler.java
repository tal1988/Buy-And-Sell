package com.example.tal.search;

//Class imports.
import android.annotation.TargetApi;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

//Opening ParseHandler class.
public class ParseHandler extends Application
{
    //Class variables.
    public final int IMG_ONE = 0;
    public final int IMG_TWO = 1;
    public final int IMG_THERE = 2;
    public final int USER = 0;
    public final int DEFULT_SIZE = 0;
    public final int FIRST_OBJECT = 0;

    public static Context context;

    //Method that executed when the activity create.
    //Here we initialize the data base.
    @Override
    public void onCreate()
    {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("UV8nmbV0K09J8w7CynIXy7ZZKAn3g4iPWxU0H2Iq")
                .clientKey("fliveBurpEZmi1iSWLwFaRFF9MPKB4NhpOL0stKC")
                .build());
    }

    //Default class constructor.
    public ParseHandler(){}

    //Class constructor that get app context and pass it to in class variable.
    public ParseHandler(Context cont){context = cont;}

    //Method that adding user to the data base after registration.
    public void addUserToDataBase(String firstName, String lastName, String userName, String password, String eMail)
    {
        final ParseObject parseObject = new ParseObject("Users");
        parseObject.put("FirstName",firstName);
        parseObject.put("LastName",lastName);
        parseObject.put("UserName",userName);
        parseObject.put("Password",password);
        parseObject.put("EMAIL",eMail);

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                if(e == null)
                {
                    Toast toast = Toast.makeText(context,"Registration ended successfully",Toast.LENGTH_LONG);
                    toast.show();
                }
                else
                {
                    e.printStackTrace();
                    Toast toast = Toast.makeText(context,"Registration failed",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    //Method that check if the user that try to log in to the system insert all the data correct.
    public void ifLoginRequestOk(final String userName, final String password)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("UserName",userName);
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                try
                {
                    if(objects.size() > DEFULT_SIZE && objects.get(USER).get("Password").equals(password))
                        MainActivity.loginError = false;
                    else
                        MainActivity.loginError = true;
                }
                catch (NullPointerException nullPointer)
                {
                    MainActivity.loginError = true;
                }
            }
        });
    }
    //Method that check if the user that want to register picked up a user name that already exist.
    public void ifUserExist(final String userName)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Users");
        query.whereEqualTo("UserName",userName);
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                if(objects.size() == 0)
                {
                    RegisterActivity.ifUserExsist = false;
                    RegisterActivity.canProceed = true;
                }

                else
                {
                    RegisterActivity.ifUserExsist = true;
                    RegisterActivity.canProceed = true;
                }
            }
        });
    }

    //Method that get for us the images of the object.
    public void getParseObjectImgs(final String objID)
    {
        final List<ParseObject> imges = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("ImageOne");
        final ParseQuery<ParseObject> query1 = ParseQuery.getQuery("ImageTwo");
        final ParseQuery<ParseObject> query2 = ParseQuery.getQuery("ImageThere");
        query.whereEqualTo("ID",objID);
        query1.whereEqualTo("ID",objID);
        query2.whereEqualTo("ID",objID);
        query.findInBackground(new FindCallback<ParseObject>()
        {
        @Override
        public void done(List<ParseObject> objects, ParseException e)
        {
               if(e == null)
               {
                    imges.add(objects.get(FIRST_OBJECT));
                    query1.findInBackground(new FindCallback<ParseObject>()
                    {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e)
                        {
                            if(e == null)
                            {
                                imges.add(objects.get(FIRST_OBJECT));
                                query2.findInBackground(new FindCallback<ParseObject>()
                                {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e)
                                    {
                                        if(e == null)
                                        {
                                            imges.add(objects.get(FIRST_OBJECT));
                                            CategoryActivity.objectsImgs.put(objID,imges);

                                            if(CategoryActivity.objectsImgs.size() == CategoryActivity.objectList.size())
                                                BuyActivity.imgsHere = true;
                                        }
                                        else
                                            e.printStackTrace();
                                    }
                                });
                            }
                            else
                                e.printStackTrace();
                        }
                    });
                }
                else
                    e.printStackTrace();
            }
        });
    }

    //Method that get for us the items list of the requested category by the user.
    public void getUserRequestCategory(String category)
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(category);
        query.findInBackground(new FindCallback<ParseObject>()
        {
            @Override
            public void done(List<ParseObject> objects, ParseException e)
            {
                CategoryActivity.objectList = objects;
                BuyActivity.categoryHere = true;
            }
        });
    }

    //Method that add mobile form to the data base.
    public void addMobileFormToDataBase(String mani, String modle, String memory, String saleArea,
                                        String contact, String price, String color,Bitmap[] bitmaps, final ProgressDialog prog)
    {
        final ParseObject parseObject = new ParseObject("Mobile");
        final ParseObject parseObjectImgOne = new ParseObject("ImageOne");
        final ParseObject parseObjectImgTwo = new ParseObject("ImageTwo");
        final ParseObject parseObjectImgThere = new ParseObject("ImageThere");
        parseObject.put("Manufacturer",mani);
        parseObject.put("Modle",modle);
        parseObject.put("Memory",memory);
        parseObject.put("SaleArea",saleArea);
        parseObject.put("Contact",contact);
        parseObject.put("Price",price);
        parseObject.put("Color",color);
        if(bitmaps[IMG_ONE] != null)
            parseObjectImgOne.put("image",bitMapToString(bitmaps[IMG_ONE]));
        if(bitmaps[IMG_TWO] != null)
            parseObjectImgTwo.put("image",bitMapToString(bitmaps[IMG_TWO]));
        if(bitmaps[IMG_THERE] != null)
            parseObjectImgThere.put("image",bitMapToString(bitmaps[IMG_THERE]));

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                parseObjectImgOne.put("ID", parseObject.getObjectId());
                parseObjectImgTwo.put("ID", parseObject.getObjectId());
                parseObjectImgThere.put("ID", parseObject.getObjectId());

                parseObjectImgOne.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        parseObjectImgTwo.saveInBackground(new SaveCallback()
                        {
                            @Override
                            public void done(ParseException e)
                            {
                                parseObjectImgThere.saveInBackground(new SaveCallback()
                                {
                                    @Override
                                    public void done(ParseException e)
                                    {
                                        if (e == null)
                                        {
                                            Toast toast = Toast.makeText(context, "Form Saved Successfully", Toast.LENGTH_LONG);
                                            toast.show();
                                            prog.dismiss();
                                            SellActivity.spinner.setSelection(FIRST_OBJECT);
                                        }
                                        else
                                        {
                                            e.printStackTrace();
                                            Toast toast = Toast.makeText(context, "Error Saveing Form", Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    //Method that add computer form to the data base.
    public void addComputerFormToDataBase(String mani, String modle, String ram, String hardisk,
                                          String processor, String color, String price, String contact, String saleArea
                                        ,Bitmap[] bitmaps, final ProgressDialog prg)
    {
        final ParseObject parseObject = new ParseObject("Computer");
        final ParseObject parseObjectImgOne = new ParseObject("ImageOne");
        final ParseObject parseObjectImgTwo = new ParseObject("ImageTwo");
        final ParseObject parseObjectImgThere = new ParseObject("ImageThere");
        parseObject.put("Manufacturer",mani);
        parseObject.put("Modle",modle);
        parseObject.put("RAM",ram);
        parseObject.put("HardDisk",hardisk);
        parseObject.put("Processor",processor);
        parseObject.put("Color",color);
        parseObject.put("Price",price);
        parseObject.put("Contact",contact);
        parseObject.put("SaleArea",saleArea);
        if(bitmaps[IMG_ONE] != null)
            parseObjectImgOne.put("image",bitMapToString(bitmaps[IMG_ONE]));
        if(bitmaps[IMG_TWO] != null)
            parseObjectImgTwo.put("image",bitMapToString(bitmaps[IMG_TWO]));
        if(bitmaps[IMG_THERE] != null)
            parseObjectImgThere.put("image",bitMapToString(bitmaps[IMG_THERE]));

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                parseObjectImgOne.put("ID",parseObject.getObjectId());
                parseObjectImgTwo.put("ID",parseObject.getObjectId());
                parseObjectImgThere.put("ID",parseObject.getObjectId());

                parseObjectImgOne.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        parseObjectImgTwo.saveInBackground(new SaveCallback()
                        {
                            @Override
                            public void done(ParseException e)
                            {
                                parseObjectImgThere.saveInBackground(new SaveCallback()
                                {
                                    @Override
                                    public void done(ParseException e)
                                    {
                                        if(e == null)
                                        {
                                            Toast toast = Toast.makeText(context,"From Saved Successfully",Toast.LENGTH_LONG);
                                            toast.show();
                                            prg.dismiss();
                                            SellActivity.spinner.setSelection(FIRST_OBJECT);
                                        }
                                        else
                                        {
                                            e.printStackTrace();
                                            Toast toast = Toast.makeText(context,"Error Saveing Form",Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    //Method that add furniture form to the data base.
    public void addFurnitureFormToDataBase(String type,String ungroup,String color,String size,
                                          String saleArea,String price,String contact
                                            ,Bitmap[] bitmaps,final ProgressDialog prg)
    {
        final ParseObject parseObject = new ParseObject("Furniture");
        final ParseObject parseObjectImgOne = new ParseObject("ImageOne");
        final ParseObject parseObjectImgTwo = new ParseObject("ImageTwo");
        final ParseObject parseObjectImgThere = new ParseObject("ImageThere");
        parseObject.put("Type",type);
        parseObject.put("Ungroup",ungroup);
        parseObject.put("Color",color);
        parseObject.put("Size",size);
        parseObject.put("SaleArea",saleArea);
        parseObject.put("Price",price);
        parseObject.put("Contact",contact);
        if(bitmaps[IMG_ONE] != null)
            parseObjectImgOne.put("image",bitMapToString(bitmaps[IMG_ONE]));
        if(bitmaps[IMG_TWO] != null)
            parseObjectImgTwo.put("image",bitMapToString(bitmaps[IMG_TWO]));
        if(bitmaps[IMG_THERE] != null)
            parseObjectImgThere.put("image",bitMapToString(bitmaps[IMG_THERE]));

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                parseObjectImgOne.put("ID",parseObject.getObjectId());
                parseObjectImgTwo.put("ID",parseObject.getObjectId());
                parseObjectImgThere.put("ID",parseObject.getObjectId());

                parseObjectImgOne.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        parseObjectImgTwo.saveInBackground(new SaveCallback()
                        {
                            @Override
                            public void done(ParseException e)
                            {
                                parseObjectImgThere.saveInBackground(new SaveCallback()
                                {
                                    @Override
                                    public void done(ParseException e)
                                    {
                                        if(e == null)
                                        {
                                            Toast toast = Toast.makeText(context,"From Saved Successfully",Toast.LENGTH_LONG);
                                            toast.show();
                                            prg.dismiss();
                                            SellActivity.spinner.setSelection(FIRST_OBJECT);
                                        }
                                        else
                                        {
                                            e.printStackTrace();
                                            Toast toast = Toast.makeText(context,"Error Saveing Form",Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    //Method that add car form to the data base.
    public void addCarFormToDataBase(String mani,String modle,String engineVolume,String year,
                                        String ownerNumber,String price,String engineType,String saleArea
                                        ,String contact,String color,Bitmap[] bitmaps,final ProgressDialog prg)
    {
        final ParseObject parseObject = new ParseObject("Car");
        final ParseObject parseObjectImgOne = new ParseObject("ImageOne");
        final ParseObject parseObjectImgTwo = new ParseObject("ImageTwo");
        final ParseObject parseObjectImgThere = new ParseObject("ImageThere");
        parseObject.put("Manufacturer",mani);
        parseObject.put("Modle",modle);
        parseObject.put("EngineVolume",engineVolume);
        parseObject.put("Year",year);
        parseObject.put("OwnerNumber",ownerNumber);
        parseObject.put("Price",price);
        parseObject.put("EngineType",engineType);
        parseObject.put("SaleArea",saleArea);
        parseObject.put("Contact",contact);
        parseObject.put("Color",color);
        if(bitmaps[IMG_ONE] != null)
            parseObjectImgOne.put("image",bitMapToString(bitmaps[IMG_ONE]));
        if(bitmaps[IMG_TWO] != null)
            parseObjectImgTwo.put("image",bitMapToString(bitmaps[IMG_TWO]));
        if(bitmaps[IMG_THERE] != null)
            parseObjectImgThere.put("image",bitMapToString(bitmaps[IMG_THERE]));

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                parseObjectImgOne.put("ID",parseObject.getObjectId());
                parseObjectImgTwo.put("ID",parseObject.getObjectId());
                parseObjectImgThere.put("ID",parseObject.getObjectId());

                parseObjectImgOne.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        parseObjectImgTwo.saveInBackground(new SaveCallback()
                        {
                            @Override
                            public void done(ParseException e)
                            {
                                parseObjectImgThere.saveInBackground(new SaveCallback()
                                {
                                    @Override
                                    public void done(ParseException e)
                                    {
                                        if(e == null)
                                        {
                                            Toast toast = Toast.makeText(context,"From Saved Successfully",Toast.LENGTH_LONG);
                                            toast.show();
                                            prg.dismiss();
                                            SellActivity.spinner.setSelection(FIRST_OBJECT);
                                        }
                                        else
                                        {
                                            e.printStackTrace();
                                            Toast toast = Toast.makeText(context,"Error Saveing Form",Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    //Method that add apartment form to the data base.
    public void addApartmentFormToDataBase(String type,String city,String neighborhood,String address,
                                     String roomsAmount,String balconiesAmount,String floorNumber,String size
                                    ,String contact,String price,Bitmap[] bitmaps,final ProgressDialog prg)
    {
        final ParseObject parseObject = new ParseObject("Apartment");
        final ParseObject parseObjectImgOne = new ParseObject("ImageOne");
        final ParseObject parseObjectImgTwo = new ParseObject("ImageTwo");
        final ParseObject parseObjectImgThere = new ParseObject("ImageThere");
        parseObject.put("Type",type);
        parseObject.put("City",city);
        parseObject.put("Neighborhood",neighborhood);
        parseObject.put("Address",address);
        parseObject.put("RoomAmount",roomsAmount);
        parseObject.put("BalconiesAmount",balconiesAmount);
        parseObject.put("FloorNumber",floorNumber);
        parseObject.put("Size",size);
        parseObject.put("Contact",contact);
        parseObject.put("Price",price);
        if(bitmaps[IMG_ONE] != null)
            parseObjectImgOne.put("image",bitMapToString(bitmaps[IMG_ONE]));
        if(bitmaps[IMG_TWO] != null)
            parseObjectImgTwo.put("image",bitMapToString(bitmaps[IMG_TWO]));
        if(bitmaps[IMG_THERE] != null)
            parseObjectImgThere.put("image",bitMapToString(bitmaps[IMG_THERE]));

        parseObject.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
                parseObjectImgOne.put("ID",parseObject.getObjectId());
                parseObjectImgTwo.put("ID",parseObject.getObjectId());
                parseObjectImgThere.put("ID",parseObject.getObjectId());

                parseObjectImgOne.saveInBackground(new SaveCallback()
                {
                    @Override
                    public void done(ParseException e)
                    {
                        parseObjectImgTwo.saveInBackground(new SaveCallback()
                        {
                            @Override
                            public void done(ParseException e)
                            {
                                parseObjectImgThere.saveInBackground(new SaveCallback()
                                {
                                    @Override
                                    public void done(ParseException e)
                                    {
                                        if(e == null)
                                        {
                                            Toast toast = Toast.makeText(context,"From Saved Successfully",Toast.LENGTH_LONG);
                                            toast.show();
                                            prg.dismiss();
                                            SellActivity.spinner.setSelection(FIRST_OBJECT);
                                        }
                                        else
                                        {
                                            e.printStackTrace();
                                            Toast toast = Toast.makeText(context,"Error Saveing Form",Toast.LENGTH_LONG);
                                            toast.show();
                                        }
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    //Method that convert for us bitmap to string(For storing in the data base.)
    @TargetApi(8)
    public String bitMapToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] bytes=baos.toByteArray();
        String temp= Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }
}
