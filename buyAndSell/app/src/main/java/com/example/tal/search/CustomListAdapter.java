package com.example.tal.search;

//Class imports.
import com.example.tal.search.models.ApartmentModel;
import com.example.tal.search.models.CarModel;
import com.example.tal.search.models.ComputerModel;
import com.example.tal.search.models.FurnitureModel;
import com.example.tal.search.models.MobileModel;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

//Opening CustomListAdapter class.
public class CustomListAdapter extends BaseAdapter
{
    //Class variables.
    public final int MOBILE = 1;
    public final int COMPUTER = 2;
    public final int FURNITURE = 3;
    public final int CAR = 4;
    public final int APARTMENT = 5;
    private final int IMG_NEW_WIDTH = 70;
    private final int IMG_NEW_HEIGHT = 70;

    protected MobileModel mobileModel;
    protected ComputerModel computerModel;
    protected FurnitureModel furnitureModel;
    protected CarModel carModel;
    protected ApartmentModel apartmentModel;

    protected List<MobileModel> mobiles;
    protected List<ComputerModel> computers;
    protected List<FurnitureModel> furniture;
    protected List<CarModel> cars;
    protected List<ApartmentModel> apartments;

    public int category;
    public boolean mobileLayout,computerLayout,furnitureLayout,carLayout,apartmentLayout;
    public TextView text1,text2,text3,text4,text5,text1Text,text2Text,text3Text,text4Text,text5Text;
    public TextView text6,text7,text8,text9,text10,text6Text,text7Text,text8Text,text9Text,text10Text;
    public ImageView imageOne,imageTwo,imageThere;


    private Activity activity;
    private LayoutInflater inflater;

    //Class constructor, get unknown list and assign it to the right model list. .
    public CustomListAdapter(Activity activity,List<?> list,int category)
    {
        this.activity = activity;
        this.category = category;

        if(category == MOBILE)
        {
            mobiles = (List<MobileModel>)list;
            mobileLayout = true;
            computerLayout = furnitureLayout = carLayout = apartmentLayout = false;
        }
        else if(category == COMPUTER)
        {
            computers = (List<ComputerModel>)list;
            computerLayout = true;
            mobileLayout = furnitureLayout = carLayout = apartmentLayout = false;
        }
        else if(category == FURNITURE)
        {
            furniture = (List<FurnitureModel>)list;
            furnitureLayout = true;
            mobileLayout = computerLayout = carLayout = apartmentLayout = false;
        }
        else if(category == CAR)
        {
            cars = (List<CarModel>)list;
            carLayout = true;
            mobileLayout = computerLayout = furnitureLayout = apartmentLayout = false;
        }
        else if(category == APARTMENT)
        {
            apartments = (List<ApartmentModel>)list;
            apartmentLayout = true;
            mobileLayout = computerLayout = furnitureLayout = carLayout = false;
        }
    }

    //Method that give us the size of each model list we have.
    @Override
    public int getCount()
    {
        if(mobileLayout)
            return mobiles.size();
        else if(computerLayout)
            return computers.size();
        else if(furnitureLayout)
            return furniture.size();
        else if(carLayout)
            return cars.size();
        else
            return apartments.size();
    }

    //Method that get for us specific item from the list by given location.
    @Override
    public Object getItem(int location)
    {
        if(mobileLayout)
            return mobiles.get(location);
        else if(computerLayout)
            return computers.get(location);
        else if(furnitureLayout)
            return furniture.get(location);
        else if(carLayout)
            return cars.get(location);
        else
            return apartments.get(location);
    }

    //Method that give us the position of specific item in the list.
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //Method that get a View that displays the data at the specified position in the data set.
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.main_item_list_layout, null);

        text1 = (TextView)convertView.findViewById(R.id.text1);
        text2 = (TextView)convertView.findViewById(R.id.text2);
        text3 = (TextView)convertView.findViewById(R.id.text3);
        text4 = (TextView)convertView.findViewById(R.id.text4);
        text5 = (TextView)convertView.findViewById(R.id.text5);
        text6 = (TextView)convertView.findViewById(R.id.text6);
        text7 = (TextView)convertView.findViewById(R.id.text7);
        text8 = (TextView)convertView.findViewById(R.id.text8);
        text9 = (TextView)convertView.findViewById(R.id.text9);
        text10 = (TextView)convertView.findViewById(R.id.text10);
        text1Text = (TextView)convertView.findViewById(R.id.text1Text);
        text2Text = (TextView)convertView.findViewById(R.id.text2Text);
        text3Text = (TextView)convertView.findViewById(R.id.text3Text);
        text4Text = (TextView)convertView.findViewById(R.id.text4Text);
        text5Text = (TextView)convertView.findViewById(R.id.text5Text);
        text6Text = (TextView)convertView.findViewById(R.id.text6Text);
        text7Text = (TextView)convertView.findViewById(R.id.text7Text);
        text8Text = (TextView)convertView.findViewById(R.id.text8Text);
        text9Text = (TextView)convertView.findViewById(R.id.text9Text);
        text10Text = (TextView)convertView.findViewById(R.id.text10Text);
        imageOne = (ImageView)convertView.findViewById(R.id.imgOne);
        imageTwo = (ImageView)convertView.findViewById(R.id.imgTwo);
        imageThere = (ImageView)convertView.findViewById(R.id.imgThere);
        clearAllTextViewAndImgs();

        if(category == MOBILE)
        {
            mobileModel = (MobileModel)getItem(position);
            text1.setText("Manufactur:");
            text2.setText("Model:");
            text3.setText("Memory:");
            text4.setText("Sale Area:");
            text5.setText("Contact:");
            text6.setText("Price:");
            text7.setText("Color:");
            text1Text.setText(mobileModel.getMani());
            text2Text.setText(mobileModel.getModle());
            text3Text.setText(mobileModel.getMemory());
            text4Text.setText(mobileModel.getSaleArea());
            text5Text.setText(mobileModel.getContact());
            text6Text.setText(mobileModel.getPrice());
            text7Text.setText(mobileModel.getMobileColor());
            if(mobileModel.getImgOne() != null)
                imageOne.setImageBitmap(Bitmap.createScaledBitmap(mobileModel.getImgOne(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(mobileModel.getImgTwo() != null)
                imageTwo.setImageBitmap(Bitmap.createScaledBitmap(mobileModel.getImgTwo(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(mobileModel.getImgThere() != null)
            imageThere.setImageBitmap(Bitmap.createScaledBitmap(mobileModel.getImgThere(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
        }
        else if(category == COMPUTER)
        {
            computerModel = (ComputerModel)getItem(position);
            text1.setText("Manufactur:");
            text2.setText("Model:");
            text3.setText("RAM:");
            text4.setText("Hard-Disk:");
            text5.setText("Processor:");
            text6.setText("Color:");
            text7.setText("Price:");
            text8.setText("Contact:");
            text9.setText("Sale Area:");
            text1Text.setText(computerModel.getMani());
            text2Text.setText(computerModel.getModle());
            text3Text.setText(computerModel.getRam());
            text4Text.setText(computerModel.getDisk());
            text5Text.setText(computerModel.getProcessor());
            text6Text.setText(computerModel.getCompColor());
            text7Text.setText(computerModel.getPrice());
            text8Text.setText(computerModel.getContact());
            text9Text.setText(computerModel.getSaleArea());
            if(computerModel.getImgOne() != null)
                imageOne.setImageBitmap(Bitmap.createScaledBitmap(computerModel.getImgOne(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(computerModel.getImgTwo() != null)
                imageTwo.setImageBitmap(Bitmap.createScaledBitmap(computerModel.getImgTwo(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(computerModel.getImgThere() != null)
                imageThere.setImageBitmap(Bitmap.createScaledBitmap(computerModel.getImgThere(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
        }
        else if(category == FURNITURE)
        {
            furnitureModel = furniture.get(position);
            text1.setText("Type:");
            text2.setText("Un-Group:");
            text3.setText("Color:");
            text4.setText("Size:");
            text5.setText("Sale Area:");
            text6.setText("Price:");
            text7.setText("Contact:");
            text1Text.setText(furnitureModel.getType());
            text2Text.setText(furnitureModel.getUnGroup());
            text3Text.setText(furnitureModel.getFurnColor());
            text4Text.setText(furnitureModel.getSize());
            text5Text.setText(furnitureModel.getSaleArea());
            text6Text.setText(furnitureModel.getPrice());
            text7Text.setText(furnitureModel.getContact());
            if(furnitureModel.getImgOne() != null)
                imageOne.setImageBitmap(Bitmap.createScaledBitmap(furnitureModel.getImgOne(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(furnitureModel.getImgTwo() != null)
                imageTwo.setImageBitmap(Bitmap.createScaledBitmap(furnitureModel.getImgTwo(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(furnitureModel.getImgThere() != null)
                imageThere.setImageBitmap(Bitmap.createScaledBitmap(furnitureModel.getImgThere(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
        }
        else if(category == CAR)
        {
            carModel = cars.get(position);
            text1.setText("Manufactur:");
            text2.setText("Model:");
            text3.setText("Engine Volume:");
            text4.setText("Engine Type:");
            text5.setText("Owners:");
            text6.setText("Price:");
            text7.setText("Year");
            text8.setText("Sale Area:");
            text9.setText("Contact:");
            text10.setText("Color:");
            text1Text.setText(carModel.getMani());
            text2Text.setText(carModel.getModle());
            text3Text.setText(carModel.getEngineVol());
            text4Text.setText(carModel.getEngineType());
            text5Text.setText(carModel.getOwnersNum());
            text6Text.setText(carModel.getPrice());
            text7Text.setText(carModel.getYear());
            text8Text.setText(carModel.getSaleArea());
            text9Text.setText(carModel.getContact());
            text10Text.setText(carModel.getCarColor());

            if(carModel.getImgOne() != null)
                imageOne.setImageBitmap(Bitmap.createScaledBitmap(carModel.getImgOne(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(carModel.getImgTwo() != null)
                imageTwo.setImageBitmap(Bitmap.createScaledBitmap(carModel.getImgTwo(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(carModel.getImgThere() != null)
                imageThere.setImageBitmap(Bitmap.createScaledBitmap(carModel.getImgThere(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
        }
        else
        {
            apartmentModel = apartments.get(position);
            text1.setText("Property Type:");
            text2.setText("City:");
            text3.setText("Neighborhood:");
            text4.setText("Address:");
            text5.setText("Rooms:");
            text6.setText("Balconies:");
            text7.setText("Floor Number:");
            text8.setText("Size:");
            text9.setText("Contact:");
            text10.setText("Price:");
            text1Text.setText(apartmentModel.getType());
            text2Text.setText(apartmentModel.getCity());
            text3Text.setText(apartmentModel.getNeigh());
            text4Text.setText(apartmentModel.getAddress());
            text5Text.setText(apartmentModel.getRooms());
            text6Text.setText(apartmentModel.getBalconies());
            text7Text.setText(apartmentModel.getFloor());
            text8Text.setText(apartmentModel.getSize());
            text9Text.setText(apartmentModel.getContact());
            text10Text.setText(apartmentModel.getPrice());
            if(apartmentModel.getImgOne() != null)
                imageOne.setImageBitmap(Bitmap.createScaledBitmap(apartmentModel.getImgOne(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(apartmentModel.getImgTwo() != null)
                imageTwo.setImageBitmap(Bitmap.createScaledBitmap(apartmentModel.getImgTwo(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
            if(apartmentModel.getImgThere() != null)
                imageThere.setImageBitmap(Bitmap.createScaledBitmap(apartmentModel.getImgThere(),IMG_NEW_WIDTH,IMG_NEW_HEIGHT,true));
        }
        return convertView;
    }

    //Get and Set methods for models layouts.
    public boolean getMobileLayot(){return this.mobileLayout;}
    public void setMobileLayout(boolean state){this.mobileLayout = state;}

    public boolean getComputerLayout(){return this.computerLayout;}
    public void setComputerLayout(boolean state){this.computerLayout = state;}

    public boolean getFurnitureLayot(){return this.furnitureLayout;}
    public void setFurnitureLayout(boolean state){this.furnitureLayout = state;}

    public boolean getCarLayot(){return this.carLayout;}
    public void setCarLayout(boolean state){this.carLayout = state;}

    public boolean getApartmentLayot(){return this.apartmentLayout;}
    public void setApartmentLayout(boolean state){this.apartmentLayout = state;}

    //Method that clear for us all the text view and the images that user fill.
    public void clearAllTextViewAndImgs()
    {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        text7.setText("");
        text8.setText("");
        text9.setText("");
        text10.setText("");
        text1Text.setText("");
        text2Text.setText("");
        text3Text.setText("");
        text4Text.setText("");
        text5Text.setText("");
        text6Text.setText("");
        text7Text.setText("");
        text8Text.setText("");
        text9Text.setText("");
        text10Text.setText("");
        imageOne.setImageBitmap(null);
        imageTwo.setImageBitmap(null);
        imageThere.setImageBitmap(null);
    }
}
