package com.example.tal.search;

//Class imports.
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//Opening RegisterActivity activity.
public class RegisterActivity extends Activity
{
    //Class variables.
    private final int STRING_DEFULT_LENGTH = 0;
    public final long DELAY = 100;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String email;
    private EditText firstNameTextField;
    private EditText lastNameTextField;
    private EditText userNameTextField;
    private EditText passWordTextField;
    private EditText confirmPasswordTextField;
    private EditText emailTextField;

    public static boolean ifUserExsist,canProceed;

    public ParseHandler parseHandler;
    public ProgressDialog progressDialog;

    //Method that executed when the activity create.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        parseHandler = new ParseHandler();
        firstName = null;
        lastName = null;
        userName = null;
        password = null;
        confirmPassword = null;
        email = null;
        ifUserExsist = canProceed = false;
        firstNameTextField = (EditText)findViewById(R.id.firstNameText);
        lastNameTextField = (EditText)findViewById(R.id.lastNameText);
        userNameTextField = (EditText)findViewById(R.id.userNameText);
        passWordTextField = (EditText)findViewById(R.id.passwordText);
        confirmPasswordTextField = (EditText)findViewById(R.id.confrimPasswordText);
        emailTextField = (EditText)findViewById(R.id.emailText);
    }

    //Method that executed when the user press the apply button in this activity.
    public void applyButtonPressed(View view)
    {
        activateProgressDaialog();
        canProceed = ifUserExsist = false;
        firstName = firstNameTextField.getText().toString();
        lastName = lastNameTextField.getText().toString();
        userName = userNameTextField.getText().toString();
        password = passWordTextField.getText().toString();
        confirmPassword = confirmPasswordTextField.getText().toString();
        email = emailTextField.getText().toString();

        if(firstName.length() == STRING_DEFULT_LENGTH || lastName.length() == STRING_DEFULT_LENGTH
                || userName.length() == STRING_DEFULT_LENGTH || password.length() == STRING_DEFULT_LENGTH
                || confirmPassword.length() == STRING_DEFULT_LENGTH || email.length() == STRING_DEFULT_LENGTH)
        {
            Toast toast = Toast.makeText(RegisterActivity.this,"There are missing details",Toast.LENGTH_LONG);
            View view1 = toast.getView();
            view1.setBackgroundResource(R.color.error);
            toast.show();
            progressDialog.dismiss();
            return;
        }
        else if (!password.equals(confirmPassword))
        {
            Toast toast = Toast.makeText(RegisterActivity.this,"Passwords not equal",Toast.LENGTH_LONG);
            View view1 = toast.getView();
            view1.setBackgroundResource(R.color.error);
            toast.show();
            progressDialog.dismiss();
            return;
        }
        else if (password.equals(confirmPassword) && password.length() < 5)
        {
            Toast toast = Toast.makeText(RegisterActivity.this,"Passwords most contains 5 character length",Toast.LENGTH_LONG);
            View view1 = toast.getView();
            view1.setBackgroundResource(R.color.error);
            toast.show();
            progressDialog.dismiss();
            return;
        }
        parseHandler.ifUserExist(userName);
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(!canProceed)
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
                continuRegisteration(firstName,lastName,userName,password,email);
            }
        }
    };

    //Method that executed only if all the data that filled by the user are good.
    public void continuRegisteration(String firstName,String lastName,String userName,String password,String email)
    {
        //Here we check if the user name that the new user picked are exist or no.
        if(!ifUserExsist)
        {
            parseHandler = new ParseHandler(getApplicationContext());
            parseHandler.addUserToDataBase(firstName,lastName,userName,password,email);
            setResult(RESULT_OK);
            progressDialog.dismiss();
            finish();
        }
        else
        {
            Toast toast = Toast.makeText(RegisterActivity.this,"User Name Already Exists",Toast.LENGTH_LONG);
            View view1 = toast.getView();
            view1.setBackgroundResource(R.color.error);
            toast.show();
            progressDialog.dismiss();
        }
    }

    //Method that activate the progress dialog for us.
    public void activateProgressDaialog()
    {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Please Wait...");
        progressDialog.setMessage("Checking Data...");
        progressDialog.show();
    }

    //Method that executed when the user pressing the return button in this activity.
    public void onReturnButtonPressedRegister(View view){finish();}
}
