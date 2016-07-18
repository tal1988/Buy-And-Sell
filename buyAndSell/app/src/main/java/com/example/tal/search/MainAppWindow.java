package com.example.tal.search;

//Class imports.
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//Opening MainAppWindow Activity.
public class MainAppWindow extends Activity
{
    //Class variables.
    public final int SELL_REQ = 0;
    public final int BUY_REQ = 1;
    public final int MAIN_REQ = 2;

    public String userName;
    public Bundle bundle;
    public TextView userNameHelloMessege;

    //Method that executed when the activity create.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_window);
        bundle = getIntent().getExtras();
        userNameHelloMessege = (TextView)findViewById(R.id.userName);
        userName = (String)bundle.get("userName");
        userNameHelloMessege.setText(userName);
    }

    //Method that executed when the user press the sell button.
    public void sellButtonPressed(View view)
    {
        Intent sell = new Intent(getApplicationContext(), SellActivity.class);
        startActivityForResult(sell, SELL_REQ);
    }

    //Method that executed when the user press the buy button.
    public void buyButtonPressed(View view)
    {
        Intent buy = new Intent(getApplicationContext(), BuyActivity.class);
        startActivityForResult(buy, BUY_REQ);
    }

    //Method that executed when the user press the disconnect button.
    public void disconnectButtonPressed(View view)
    {
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(mainActivity, MAIN_REQ);
    }
}
