package com.example.tal.search;

//Class imports.
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Opening MainActivity activity.
public class MainActivity extends Activity
{
    //Class variables.
    public final int REGEISTER_REQ = 0;
    public final long DURATION = 5000;
    public final int STRING_DEFULT_LENGTH = 0;
    public final long DELAY = 1500;

    public static boolean loginError;

    public String userName;
    public String passWord;
    public Button registerButton;
    public Button loginButton;
    public EditText userNameTextField;
    public EditText passWordTextField;
    public ParseHandler parseHandler;
    public ProgressDialog progressDialog;

    //Method that executed when the activity create.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View view = findViewById(R.id.mainActivity);
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,android.R.anim.fade_in);
        animation.setDuration(DURATION);
        view.setAnimation(animation);
        userName = null;
        passWord = null;
        registerButton = (Button)findViewById(R.id.button);
        loginButton = (Button)findViewById(R.id.button2);
        userNameTextField = (EditText)findViewById(R.id.editText);
        passWordTextField = (EditText)findViewById(R.id.editText2);

        userNameTextField.setText("");
        passWordTextField.setText("");
    }

    //Method that executed when register button pressed.
    public void registerButtonPressed(View view)
    {
        Intent regIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivityForResult(regIntent,REGEISTER_REQ);
    }

    //Method that executed when login button pressed.
    public void loginButtonPressed(View view)
    {
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Loging In...");
        progressDialog.show();
        userName = userNameTextField.getText().toString();
        passWord = passWordTextField.getText().toString();

        if(userName.length() == STRING_DEFULT_LENGTH || passWord.length() == STRING_DEFULT_LENGTH)
        {
            Toast toast = Toast.makeText(MainActivity.this,"User Name Or Password Are Missing",Toast.LENGTH_LONG);
            View view1 = toast.getView();
            view1.setBackgroundResource(R.color.error);
            toast.show();
            progressDialog.dismiss();
        }
        else
        {
            parseHandler = new ParseHandler(getApplicationContext());
            parseHandler.ifLoginRequestOk(userName,passWord);

            new Thread()
            {
                public void run()
                {
                    try
                    {
                        sleep(DELAY);
                        handler.sendEmptyMessage(0);
                    }
                    catch(Exception e)
                    {
                        Log.e("threadmessage",e.getMessage());
                    }
                }
            }.start();
        }
    }

    //This handler allows you to send and process Message and Runnable objects associated with a thread's MessageQueue
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(loginError == false)
            {
                progressDialog.dismiss();
                Intent logIntent = new Intent(getApplicationContext(), MainAppWindow.class);
                logIntent.putExtra("userName",userName);
                startActivity(logIntent);
            }
            else
            {
                Toast toast = Toast.makeText(MainActivity.this,"User Name Or Password Incurrect",Toast.LENGTH_LONG);
                View view1 = toast.getView();
                view1.setBackgroundResource(R.color.error);
                toast.show();
                progressDialog.dismiss();
            }
        }
    };

    //Method that executed when the user return from activity that activate from this activity by the method startAvtivityForResult.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {}
}