package com.example.tal.search;

//Class imports.
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//Opening SellActivity activity.
public class SellActivity extends Activity
{
    //Class variables.
    private final String[] sellItems = {"","Mobile Phone","Computer","Furniture","Car","Apartment"};
    private final int IMG_ONE = 0;
    private final int IMG_TWO = 1;
    private final int IMG_THERE = 2;
    private final int MAX_IMG = 3;
    private final int IMG_NEW_WIDTH = 70;
    private final int IMG_NEW_HEIGHT = 70;
    private final long DEFULT_CHOISE = 0;
    private final long MOBILE_PHONE = 1;
    private final long COMPUTER = 2;
    private final long FURNITURE = 3;
    private final long CAR = 4;
    private final long APARTMENT = 5;
    private final int NO_INPUT = 0;
    private final int CAMERA_REQUEST = 1;
    private final int SEE = 0;
    private final int DONT_SEE = 1;
    private final int CLEAR_PHOTOS = 2;

    public int imageCounter;
    public ParseHandler parseHandler;
    public TextView imageOneText,imageTwoText,imageThereText;
    public ImageView imageOne,imageTwo,imageThere;
    public Bitmap[] bitmaps;
    public ProgressDialog progressDialog;
    public String photo;

    protected static Spinner spinner;
    protected ArrayAdapter<String> arrayAdapter;

    //Flags to know the type of form user is filling.
    protected boolean mobileForm,computerForm,furnitureForm,carForm,apartmentForm,isImageFitToScreen;

    //General edit text
    public EditText manufacturer,modle,saleArea,contact,price,color;
    //Phone edit text
    public EditText phoneMemory;
    //Computer edit text.
    public EditText computerRam,computerHardDisk,computerProcessor;
    //Furniture edit text.
    public EditText furnitureType,furnitureCanBeUngroup,furnitureSize;
    //Car edit text.
    public EditText carEngineVolume,carYear,carOwners,carEngineType;
    //Apartment edit text.
    public EditText apartmentType,apartmentCity,apartmentNeighborhood,apartmentAddress,
                    apartmentRooms,apartmentBalconies,apartmentFloor,apartmentSize;

    //Method that executed when the activity create.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        parseHandler = new ParseHandler();
        spinner =(Spinner)findViewById(R.id.sellItmes);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_itme,sellItems);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new SpinnerActivity());
        mobileForm = computerForm = furnitureForm = carForm = apartmentForm = isImageFitToScreen = false;

        bitmaps = new Bitmap[3];
        imageOne = (ImageView)findViewById(R.id.image1);
        imageTwo = (ImageView)findViewById(R.id.image2);
        imageThere = (ImageView)findViewById(R.id.image3);
        imageOneText = (TextView)findViewById(R.id.image1Text);
        imageTwoText = (TextView)findViewById(R.id.image2Text);
        imageThereText = (TextView)findViewById(R.id.image3Text);

        //general edit text initialize.
        manufacturer = (EditText)findViewById(R.id.manufacturer);
        modle = (EditText)findViewById(R.id.modle);
        saleArea = (EditText)findViewById(R.id.saleArea);
        contact = (EditText)findViewById(R.id.contact);
        price = (EditText)findViewById(R.id.price);
        color = (EditText)findViewById(R.id.color);

        //Phone edit text initialize.
        phoneMemory = (EditText)findViewById(R.id.phoneMemory);

        //Computer edit text initialize.
        computerRam = (EditText)findViewById(R.id.computerRam);
        computerHardDisk = (EditText)findViewById(R.id.computerHardDisk);
        computerProcessor = (EditText)findViewById(R.id.computerProcessor);

        //Furniture edit text initialize.
        furnitureType = (EditText)findViewById(R.id.furnitureType);
        furnitureCanBeUngroup = (EditText)findViewById(R.id.furnitureUngroup);
        furnitureSize = (EditText)findViewById(R.id.furnitureSize);

        //Car edit text initialize.
        carEngineVolume = (EditText)findViewById(R.id.carEngineVolume);
        carYear  = (EditText)findViewById(R.id.carYear);
        carOwners = (EditText)findViewById(R.id.carOwnerNumber);
        carEngineType = (EditText)findViewById(R.id.carEngineType);

        //Apartment edit text initialize.
        apartmentType = (EditText)findViewById(R.id.apartmentType);
        apartmentCity = (EditText)findViewById(R.id.apartmentCity);
        apartmentNeighborhood = (EditText)findViewById(R.id.apartmentNeighborhood);
        apartmentAddress = (EditText)findViewById(R.id.apartmentAddress);
        apartmentRooms = (EditText)findViewById(R.id.apartmentRooms);
        apartmentBalconies = (EditText)findViewById(R.id.apartmentBalcony);
        apartmentFloor = (EditText)findViewById(R.id.apartmentFloor);
        apartmentSize = (EditText)findViewById(R.id.apartmentSize);
    }

    //Opening inner class for spinner (drop down list).
    private class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener
    {
        //Method that executed when the user select item from the drop down list.
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
        {
            setPhotoVisibilty(SEE);
            setPhotoVisibilty(CLEAR_PHOTOS);
            imageCounter = 0;

            if(id == MOBILE_PHONE)
            {
                clearAllEditTexts();
                mobileForm = true;
                computerForm = furnitureForm = carForm = apartmentForm = false;
                furnitureType.setVisibility(EditText.INVISIBLE);
                furnitureCanBeUngroup.setVisibility(EditText.INVISIBLE);
                furnitureSize.setVisibility(EditText.INVISIBLE);
                computerRam.setVisibility(EditText.INVISIBLE);
                computerHardDisk.setVisibility(EditText.INVISIBLE);
                computerProcessor.setVisibility(EditText.INVISIBLE);
                carEngineVolume.setVisibility(EditText.INVISIBLE);
                carYear.setVisibility(EditText.INVISIBLE);
                carOwners.setVisibility(EditText.INVISIBLE);
                carEngineType.setVisibility(EditText.INVISIBLE);
                apartmentType.setVisibility(EditText.INVISIBLE);
                apartmentCity.setVisibility(EditText.INVISIBLE);
                apartmentNeighborhood.setVisibility(EditText.INVISIBLE);
                apartmentAddress.setVisibility(EditText.INVISIBLE);
                apartmentRooms.setVisibility(EditText.INVISIBLE);
                apartmentBalconies.setVisibility(EditText.INVISIBLE);
                apartmentFloor.setVisibility(EditText.INVISIBLE);
                apartmentSize.setVisibility(EditText.INVISIBLE);

                manufacturer.setVisibility(EditText.VISIBLE);
                modle.setVisibility(EditText.VISIBLE);
                phoneMemory.setVisibility(EditText.VISIBLE);
                saleArea.setVisibility(EditText.VISIBLE);
                contact.setVisibility(EditText.VISIBLE);
                price.setVisibility(EditText.VISIBLE);
                color.setVisibility(EditText.VISIBLE);
            }
            else if(id == COMPUTER)
            {
                clearAllEditTexts();
                computerForm = true;
                mobileForm = furnitureForm = carForm = apartmentForm = false;

                phoneMemory.setVisibility(EditText.INVISIBLE);
                furnitureType.setVisibility(EditText.INVISIBLE);
                furnitureCanBeUngroup.setVisibility(EditText.INVISIBLE);
                furnitureSize.setVisibility(EditText.INVISIBLE);
                carEngineVolume.setVisibility(EditText.INVISIBLE);
                carYear.setVisibility(EditText.INVISIBLE);
                carOwners.setVisibility(EditText.INVISIBLE);
                carEngineType.setVisibility(EditText.INVISIBLE);
                apartmentType.setVisibility(EditText.INVISIBLE);
                apartmentCity.setVisibility(EditText.INVISIBLE);
                apartmentNeighborhood.setVisibility(EditText.INVISIBLE);
                apartmentAddress.setVisibility(EditText.INVISIBLE);
                apartmentRooms.setVisibility(EditText.INVISIBLE);
                apartmentBalconies.setVisibility(EditText.INVISIBLE);
                apartmentFloor.setVisibility(EditText.INVISIBLE);
                apartmentSize.setVisibility(EditText.INVISIBLE);

                manufacturer.setVisibility(EditText.VISIBLE);
                modle.setVisibility(EditText.VISIBLE);
                saleArea.setVisibility(EditText.VISIBLE);
                contact.setVisibility(EditText.VISIBLE);
                price.setVisibility(EditText.VISIBLE);
                color.setVisibility(EditText.VISIBLE);

                computerRam.setVisibility(EditText.VISIBLE);
                computerHardDisk.setVisibility(EditText.VISIBLE);
                computerProcessor.setVisibility(EditText.VISIBLE);
            }
            else if(id == FURNITURE)
            {
                clearAllEditTexts();
                furnitureForm = true;
                mobileForm = computerForm = carForm = apartmentForm = false;

                phoneMemory.setVisibility(EditText.INVISIBLE);
                computerRam.setVisibility(EditText.INVISIBLE);
                computerHardDisk.setVisibility(EditText.INVISIBLE);
                computerProcessor.setVisibility(EditText.INVISIBLE);
                carEngineVolume.setVisibility(EditText.INVISIBLE);
                carYear.setVisibility(EditText.INVISIBLE);
                carOwners.setVisibility(EditText.INVISIBLE);
                carEngineType.setVisibility(EditText.INVISIBLE);
                apartmentType.setVisibility(EditText.INVISIBLE);
                apartmentCity.setVisibility(EditText.INVISIBLE);
                apartmentNeighborhood.setVisibility(EditText.INVISIBLE);
                apartmentAddress.setVisibility(EditText.INVISIBLE);
                apartmentRooms.setVisibility(EditText.INVISIBLE);
                apartmentBalconies.setVisibility(EditText.INVISIBLE);
                apartmentFloor.setVisibility(EditText.INVISIBLE);
                apartmentSize.setVisibility(EditText.INVISIBLE);

                saleArea.setVisibility(EditText.VISIBLE);
                contact.setVisibility(EditText.VISIBLE);
                price.setVisibility(EditText.VISIBLE);
                color.setVisibility(EditText.VISIBLE);
                furnitureType.setVisibility(EditText.VISIBLE);
                furnitureCanBeUngroup.setVisibility(EditText.VISIBLE);
                furnitureSize.setVisibility(EditText.VISIBLE);
            }
            else if(id == CAR)
            {
                clearAllEditTexts();
                carForm = true;
                mobileForm = computerForm = furnitureForm = apartmentForm = false;

                phoneMemory.setVisibility(EditText.INVISIBLE);
                computerRam.setVisibility(EditText.INVISIBLE);
                computerHardDisk.setVisibility(EditText.INVISIBLE);
                computerProcessor.setVisibility(EditText.INVISIBLE);
                furnitureType.setVisibility(EditText.INVISIBLE);
                furnitureCanBeUngroup.setVisibility(EditText.INVISIBLE);
                furnitureSize.setVisibility(EditText.INVISIBLE);
                apartmentType.setVisibility(EditText.INVISIBLE);
                apartmentCity.setVisibility(EditText.INVISIBLE);
                apartmentNeighborhood.setVisibility(EditText.INVISIBLE);
                apartmentAddress.setVisibility(EditText.INVISIBLE);
                apartmentRooms.setVisibility(EditText.INVISIBLE);
                apartmentBalconies.setVisibility(EditText.INVISIBLE);
                apartmentFloor.setVisibility(EditText.INVISIBLE);
                apartmentSize.setVisibility(EditText.INVISIBLE);

                manufacturer.setVisibility(EditText.VISIBLE);
                modle.setVisibility(EditText.VISIBLE);
                saleArea.setVisibility(EditText.VISIBLE);
                contact.setVisibility(EditText.VISIBLE);
                price.setVisibility(EditText.VISIBLE);
                color.setVisibility(EditText.VISIBLE);
                carEngineVolume.setVisibility(EditText.VISIBLE);
                carYear.setVisibility(EditText.VISIBLE);
                carOwners.setVisibility(EditText.VISIBLE);
                carEngineType.setVisibility(EditText.VISIBLE);
            }
            else if(id == APARTMENT)
            {
                clearAllEditTexts();
                apartmentForm = true;
                mobileForm = computerForm = furnitureForm = carForm = false;

                phoneMemory.setVisibility(EditText.INVISIBLE);
                computerRam.setVisibility(EditText.INVISIBLE);
                computerHardDisk.setVisibility(EditText.INVISIBLE);
                computerProcessor.setVisibility(EditText.INVISIBLE);
                furnitureType.setVisibility(EditText.INVISIBLE);
                furnitureCanBeUngroup.setVisibility(EditText.INVISIBLE);
                furnitureSize.setVisibility(EditText.INVISIBLE);
                carEngineVolume.setVisibility(EditText.INVISIBLE);
                carYear.setVisibility(EditText.INVISIBLE);
                carOwners.setVisibility(EditText.INVISIBLE);
                carEngineType.setVisibility(EditText.INVISIBLE);

                apartmentType.setVisibility(EditText.VISIBLE);
                apartmentCity.setVisibility(EditText.VISIBLE);
                apartmentNeighborhood.setVisibility(EditText.VISIBLE);
                apartmentAddress.setVisibility(EditText.VISIBLE);
                apartmentRooms.setVisibility(EditText.VISIBLE);
                apartmentBalconies.setVisibility(EditText.VISIBLE);
                apartmentFloor.setVisibility(EditText.VISIBLE);
                apartmentSize.setVisibility(EditText.VISIBLE);
                contact.setVisibility(EditText.VISIBLE);
                price.setVisibility(EditText.VISIBLE);
            }
            else if(id == DEFULT_CHOISE)
            {
                setAllEditTextInvisible();
                clearAllEditTexts();
                setPhotoVisibilty(DONT_SEE);
            }
        }

        //Method that executed when the user do not select item.
        //We dont use this method because we have default option the we create.
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    //Method that executed when the user press the apply button in this activity.
    public void onApplyButtonPressed(View view)
    {
        if(spinner.getSelectedItemId() == DEFULT_CHOISE)
            return;

        imageCounter = 0;
        progressDialog = new ProgressDialog(SellActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Saving Form...");
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        progressDialog.show();
                        if(mobileForm == true)
                            sendMobileForm(progressDialog);
                        else if(computerForm == true)
                            sendComputerForm(progressDialog);
                        else if(furnitureForm == true)
                            sendFurnitureForm(progressDialog);
                        else if(carForm == true)
                            sendCarForm(progressDialog);
                        else if(apartmentForm == true)
                            sendApartmentForm(progressDialog);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(SellActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    //Method that executed when the return button pressed by the user in this activity.
    public void onReturnButtonPressed(View view)
    {
        imageCounter = 0;
        setAllEditTextInvisible();
        clearAllEditTexts();
        finish();
    }

    //Method that set all the edit text fields that we have in this activity to invisible.
    protected void setAllEditTextInvisible()
    {
        manufacturer.setVisibility(EditText.INVISIBLE);
        modle.setVisibility(EditText.INVISIBLE);
        saleArea.setVisibility(EditText.INVISIBLE);
        price.setVisibility(EditText.INVISIBLE);
        contact.setVisibility(EditText.INVISIBLE);
        color.setVisibility(EditText.INVISIBLE);
        phoneMemory.setVisibility(EditText.INVISIBLE);
        computerRam.setVisibility(EditText.INVISIBLE);
        computerHardDisk.setVisibility(EditText.INVISIBLE);
        computerProcessor.setVisibility(EditText.INVISIBLE);
        furnitureType.setVisibility(EditText.INVISIBLE);
        furnitureCanBeUngroup.setVisibility(EditText.INVISIBLE);
        furnitureSize.setVisibility(EditText.INVISIBLE);
        carEngineVolume.setVisibility(EditText.INVISIBLE);
        carYear.setVisibility(EditText.INVISIBLE);
        carOwners.setVisibility(EditText.INVISIBLE);
        carEngineType.setVisibility(EditText.INVISIBLE);
        apartmentType.setVisibility(EditText.INVISIBLE);
        apartmentCity.setVisibility(EditText.INVISIBLE);
        apartmentNeighborhood.setVisibility(EditText.INVISIBLE);
        apartmentAddress.setVisibility(EditText.INVISIBLE);
        apartmentRooms.setVisibility(EditText.INVISIBLE);
        apartmentBalconies.setVisibility(EditText.INVISIBLE);
        apartmentFloor.setVisibility(EditText.INVISIBLE);
        apartmentSize.setVisibility(EditText.INVISIBLE);
    }

    //Method that clear all the edit text from text in this activity.
    protected void clearAllEditTexts()
    {
        manufacturer.setText("");
        modle.setText("");
        saleArea.setText("");
        price.setText("");
        contact.setText("");
        color.setText("");
        phoneMemory.setText("");
        computerRam.setText("");
        computerHardDisk.setText("");
        computerProcessor.setText("");
        furnitureType.setText("");
        furnitureCanBeUngroup.setText("");
        furnitureSize.setText("");
        carEngineVolume.setText("");
        carYear.setText("");
        carOwners.setText("");
        carEngineType.setText("");
        apartmentType.setText("");
        apartmentCity.setText("");
        apartmentNeighborhood.setText("");
        apartmentAddress.setText("");
        apartmentRooms.setText("");
        apartmentBalconies.setText("");
        apartmentFloor.setText("");
        apartmentSize.setText("");
    }

    //Method that set the image view to invisible in this activity.
    public void setPhotoVisibilty(int state)
    {
        if(state == SEE)
        {
            imageOne.setVisibility(ImageView.VISIBLE);
            imageTwo.setVisibility(ImageView.VISIBLE);
            imageThere.setVisibility(ImageView.VISIBLE);
            imageOneText.setVisibility(TextView.VISIBLE);
            imageTwoText.setVisibility(TextView.VISIBLE);
            imageThereText.setVisibility(TextView.VISIBLE);
        }
        else if(state == DONT_SEE)
        {
            imageOne.setVisibility(ImageView.INVISIBLE);
            imageTwo.setVisibility(ImageView.INVISIBLE);
            imageThere.setVisibility(ImageView.INVISIBLE);
            imageOneText.setVisibility(TextView.INVISIBLE);
            imageTwoText.setVisibility(TextView.INVISIBLE);
            imageThereText.setVisibility(TextView.INVISIBLE);
        }
        else
        {
            imageOne.setImageDrawable(null);
            imageTwo.setImageDrawable(null);
            imageThere.setImageDrawable(null);
        }
    }

    //Method that send the mobile form to a method in the pars handler to save it in the data base.
    protected void sendMobileForm(ProgressDialog prg)
    {
        if(manufacturer.length() == NO_INPUT || modle.length() == NO_INPUT || phoneMemory.length() == NO_INPUT
                || saleArea.length() == NO_INPUT || contact.length() == NO_INPUT
                || price.length() == NO_INPUT || color.length() == NO_INPUT)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"There Are Missing Details",Toast.LENGTH_LONG);
            toast.show();
            prg.dismiss();
        }
        else
        {
            parseHandler.addMobileFormToDataBase(manufacturer.getText().toString(),modle.getText().toString(),
                    phoneMemory.getText().toString(),saleArea.getText().toString(),contact.getText().toString(),
                    price.getText().toString(),color.getText().toString(),bitmaps,prg);
        }
    }

    //Method that send the computer form to a method in the pars handler to save it in the data base.
    protected void sendComputerForm(ProgressDialog prg)
    {
        if(manufacturer.length() == NO_INPUT || modle.length() == NO_INPUT || computerRam.length() == NO_INPUT
                ||computerHardDisk.length() == NO_INPUT || computerProcessor.length() == NO_INPUT
                ||color.length() == NO_INPUT || price.length() == NO_INPUT || contact.length() == NO_INPUT
                ||saleArea.length() == NO_INPUT)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"There Are Missing Details",Toast.LENGTH_LONG);
            toast.show();
            prg.dismiss();
        }
        else
        {
            parseHandler.addComputerFormToDataBase(manufacturer.getText().toString(),modle.getText().toString(),
                    computerRam.getText().toString(),computerHardDisk.getText().toString(),
                    computerProcessor.getText().toString(),color.getText().toString(),price.getText().toString(),
                    contact.getText().toString(),saleArea.getText().toString(),bitmaps,prg);
        }
    }

    //Method that send the furniture form to a method in the pars handler to save it in the data base.
    protected void sendFurnitureForm(ProgressDialog prg)
    {
        if(furnitureType.length() == NO_INPUT || furnitureCanBeUngroup.length() == NO_INPUT ||
                color.length() == NO_INPUT || furnitureSize.length() == NO_INPUT || saleArea.length() == NO_INPUT ||
                price.length() == NO_INPUT || contact.length() == NO_INPUT)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"There Are Missing Details",Toast.LENGTH_LONG);
            toast.show();
            prg.dismiss();
        }
        else
        {
            parseHandler.addFurnitureFormToDataBase(furnitureType.getText().toString(),furnitureCanBeUngroup.getText().toString()
            ,color.getText().toString(),furnitureSize.getText().toString(),saleArea.getText().toString(),
                    price.getText().toString(),contact.getText().toString(),bitmaps,prg);
        }
    }

    //Method that send the car form to a method in the pars handler to save it in the data base.
    protected void sendCarForm(ProgressDialog prg)
    {
        if(manufacturer.length() == NO_INPUT || modle.length() == NO_INPUT || carEngineVolume.length() == NO_INPUT ||
                carYear.length() == NO_INPUT || carOwners.length() == NO_INPUT || price.length() == NO_INPUT ||
                carEngineType.length() == NO_INPUT || saleArea.length() == NO_INPUT || contact.length() == NO_INPUT ||
                color.length() == NO_INPUT)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"There Are Missing Details",Toast.LENGTH_LONG);
            toast.show();
            prg.dismiss();
        }
        else
        {
            parseHandler.addCarFormToDataBase(manufacturer.getText().toString(),modle.getText().toString()
            ,carEngineVolume.getText().toString(),carYear.getText().toString(),carOwners.getText().toString()
            ,price.getText().toString(),carEngineType.getText().toString(),saleArea.getText().toString()
            ,contact.getText().toString(),color.getText().toString(),bitmaps,prg);
        }
    }

    //Method that send the apartment form to a method in the pars handler to save it in the data base.
    protected void sendApartmentForm(ProgressDialog prg)
    {
        if(apartmentType.length() == NO_INPUT || apartmentCity.length() == NO_INPUT ||
                apartmentNeighborhood.length() == NO_INPUT || apartmentAddress.length() == NO_INPUT ||
                apartmentRooms.length() == NO_INPUT || apartmentBalconies.length() == NO_INPUT ||
                apartmentFloor.length() == NO_INPUT || apartmentSize.length() == NO_INPUT ||
                contact.length() == NO_INPUT || price.length() == NO_INPUT)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"There Are Missing Details",Toast.LENGTH_LONG);
            toast.show();
            prg.dismiss();
        }
        else
        {
            parseHandler.addApartmentFormToDataBase(apartmentType.getText().toString(),apartmentCity.getText().toString()
            ,apartmentNeighborhood.getText().toString(),apartmentAddress.getText().toString(),apartmentRooms.getText().toString()
            ,apartmentBalconies.getText().toString(),apartmentFloor.getText().toString(),apartmentSize.getText().toString()
            ,contact.getText().toString(),price.getText().toString(),bitmaps,prg);
        }
    }

    //Method that executed when the user press on the take photo button.
    //This method activate the camera activity.
    public void ontakePhotoButtonClicked(View view)
    {
        if(spinner.getSelectedItemId() == DEFULT_CHOISE)
            return;

        if(imageCounter < MAX_IMG)
        {
            Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera,CAMERA_REQUEST);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"You Cant Take More Photos", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Method that executed when the user return from activity that activate from this activity by the method startAvtivityForResult.
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == RESULT_OK)
        {
            if (requestCode == CAMERA_REQUEST || photo != null)
            {
                bitmaps[imageCounter] = (Bitmap) data.getExtras().get("data");
                Bitmap resized = Bitmap.createScaledBitmap(bitmaps[imageCounter], IMG_NEW_WIDTH, IMG_NEW_HEIGHT, true);
                if (imageCounter == IMG_ONE)
                {
                    imageOne.setImageBitmap(resized);
                    imageOneText.setVisibility(TextView.INVISIBLE);
                    imageCounter++;
                }
                else if (imageCounter == IMG_TWO)
                {
                    imageTwo.setImageBitmap(resized);
                    imageTwoText.setVisibility(TextView.INVISIBLE);
                    imageCounter++;
                }
                else if (imageCounter == IMG_THERE)
                {
                    imageThere.setImageBitmap(resized);
                    imageThereText.setVisibility(TextView.INVISIBLE);
                    imageCounter++;
                }
          }
        }
    }
}
