package com.example.tal.search;

//import's that we needed.
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.parse.ParseObject;
import java.util.HashMap;
import java.util.List;

//Open BuyActivity activity.
public class BuyActivity extends Activity
{
    //Class variables.
    public final int MOBILE = 1;
    public final int COMPUTER = 2;
    public final int FURNITURE = 3;
    public final int CAR = 4;
    public final int APARTMENT = 5;
    public final long DELAY = 100;//Sleeping delay for threads.

    public static boolean imgsHere;
    public static boolean categoryHere;

    public Intent intent;
    public ParseHandler parseHandler;
    public ProgressDialog progressDialog;

    //Method that executed when the activity create.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        imgsHere = false;
        categoryHere = false;
        parseHandler = new ParseHandler();
        intent = new Intent(BuyActivity.this,CategoryActivity.class);
    }

    //Method that called when user pressing the mobile button.
    //It will take the user to a mobiles to sell list.
    public void mobilePressed(View view) throws InterruptedException
    {
        imgsHere = false;//flag that will tell us when we got the images from the data base.
        categoryHere = false;//flag that will tell us when we got the list of items from the data base.
        activateProgressDaialog();
        parseHandler.getUserRequestCategory("Mobile");
        intent.putExtra("category",MOBILE);

        //Thread that stop the main thread untill getUserRequestCategory method finish.
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!categoryHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY",1);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //Method that called when user pressing the computer button.
    //It will take the user to a computers to sell list.
    public void computerPressed(View view)
    {
        imgsHere = false;
        categoryHere = false;
        activateProgressDaialog();
        parseHandler.getUserRequestCategory("Computer");
        intent.putExtra("category",COMPUTER);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!categoryHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY",1);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //Method that called when user pressing the furniture button.
    //It will take the user to a furnitures to sell list.
    public void furniturePressed(View view)
    {
        imgsHere = false;
        categoryHere = false;
        activateProgressDaialog();
        parseHandler.getUserRequestCategory("Furniture");
        intent.putExtra("category",FURNITURE);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!categoryHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY",1);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //Method that called when user pressing the car button.
    //It will take the user to a cars to sell list.
    public void carPressed(View view)
    {
        imgsHere = false;
        categoryHere = false;
        activateProgressDaialog();
        parseHandler.getUserRequestCategory("Car");
        intent.putExtra("category",CAR);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!categoryHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY",1);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //Method that called when user pressing the apartment button.
    //It will take the user to a apartments to sell list.
    public void apartmentPressed(View view)
    {
        imgsHere = false;
        categoryHere = false;
        activateProgressDaialog();
        parseHandler.getUserRequestCategory("Apartment");
        intent.putExtra("category",APARTMENT);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!categoryHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY",1);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //This handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(msg.getData().getInt("KEY") == 1)
            {
                try
                {
                    //If we have the items list go bring images.
                    //Else we have no data to show.
                    if(CategoryActivity.objectList.size() != 0)
                        getImgs();
                    else
                    {
                        progressDialog.dismiss();
                        Toast toast = Toast.makeText(BuyActivity.this,"No Data To Show.",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            //if we got the images.
            //Else some thing went wrong.
            if(msg.getData().getInt("KEY") == 2)
            {
                if(imgsHere)
                {
                    startActivity(intent);
                    progressDialog.dismiss();
                }
                else
                {
                    Toast toast = Toast.makeText(BuyActivity.this,"Something Went Wrong,Try Again In a Few Seconds.",Toast.LENGTH_LONG);
                    View view1 = toast.getView();
                    view1.setBackgroundResource(R.color.error);
                    toast.show();
                }
            }
        }
    };

    //Method that bring to us the images from the data base.
    public void getImgs() throws InterruptedException
    {
        CategoryActivity.objectsImgs = new HashMap<String,List<ParseObject>>();
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                for (int i = 0; i < CategoryActivity.objectList.size(); i++)
                {
                    parseHandler.getParseObjectImgs(CategoryActivity.objectList.get(i).getObjectId().toString());
                }
                //While we did not get the images stop the main thread.
                while (!imgsHere)
                {
                    try
                    {
                        sleep(DELAY);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putInt("KEY", 2);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        thread.start();
    }

    //Method that activate the progress dialog.
    public void activateProgressDaialog()
    {
        progressDialog = new ProgressDialog(BuyActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Importing Data...");
        progressDialog.show();
    }

    //Method that executed when return button is pressed.
    //This method finish the current activity.
    public void onReturnButtonPressedInBuy(View view) {finish();}
}
