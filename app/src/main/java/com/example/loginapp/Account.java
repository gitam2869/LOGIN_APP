package com.example.loginapp;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.database.LoginContract.LoginEntry;
import com.example.loginapp.database.LoginDbHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends AppCompatActivity
{
//    //Spinner Object without memory alocation
//    private Spinner mSpinner;
//
//    //define textview object
//    TextView mDisplayDate;


    //EditText field to First name of user
    private EditText mFirstNameEditText;

    //EditText field to Last Name of user
    private EditText mLastNameEditText;

    //EditText field to Phone Number of user
    private EditText mPhoneNumberEditText;

    //EditText field to Email Id of user
    private EditText mEmailIdEditText;

    //EditText field to Password of User
    private EditText mPasswordEditText;

    //Spinner field for post
    private Spinner mPostSpinner;


    private int mPost = LoginEntry.POST_LECTURER;

    //Spinner field to gender of user
    private Spinner mGenderSpinner;

    private int mGender = LoginEntry.GENDER_OTHER;

    //Button for Register
    private Button mRegisterButton;

    //Textview for Log in
    private TextView mLogInTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account_activity);

        mFirstNameEditText =(EditText)findViewById(R.id.editText_first_name);
        mLastNameEditText = (EditText)findViewById(R.id.editText_last_name);
        mPhoneNumberEditText =(EditText)findViewById(R.id.editText_phone_number);
        mEmailIdEditText = (EditText) findViewById(R.id.editText_email_id_sign_up);
        mPasswordEditText =(EditText) findViewById(R.id.editText_pass_sign_up);
        mPostSpinner = (Spinner)findViewById(R.id.spinner_post);
        mGenderSpinner=(Spinner) findViewById(R.id.spinner_gender);
        mRegisterButton = (Button) findViewById(R.id.button_register);
        mLogInTextView =  (TextView) findViewById(R.id.textview_log_in);

        setUpSpinnerPost();
        setUpSpinnerGender();

        mLogInTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Account.this,Welcome.class);
                startActivity(i);
            }
        });
        final LoginDbHelper mLoginDbHelper = new LoginDbHelper(this);


        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //insert data
                insertUserData();
                //exit acitivity
//                finish();
//                Intent i = new Intent(Account.this,Welcome.class);
//                startActivity(i);
            }
        });


    }


    public void insertUserData()
    {

        //Read data from user
        //using trim for eliminate leading and trailing white spaces
//        byte[] data = getBitmapArray();

        String firstNameString = mFirstNameEditText.getText().toString().trim();
        String lastNameString = mLastNameEditText.getText().toString().trim();
        String phoneNumberString = mPhoneNumberEditText.getText().toString().trim();
        String emailIdString = mEmailIdEditText.getText().toString().trim();
        String passwordString = mPasswordEditText.getText().toString().trim();
//        String post = mDateOfBirthTextView.getText().toString().trim();

        //String to log for phone number
//        long phoneNumberLong = Long.parseLong(phoneNumberString);

//        //create database helper
//        LoginDbHelper mLoginDbHelper = new LoginDbHelper(this);
//
//        //get database in write mode
//        SQLiteDatabase db = mLoginDbHelper.getWritableDatabase();

        //Create ContentValues in which keys are colulmn names
        //and values are user info
        ContentValues contentValues = new ContentValues();

        contentValues.put(LoginEntry.COLUMN_FIRST_NAME,firstNameString);
        contentValues.put(LoginEntry.COLUMN_LAST_NAME,lastNameString);
        contentValues.put(LoginEntry.COLUMN_PHONE_NUMBER,phoneNumberString);
        contentValues.put(LoginEntry.COLUMN_EMAIL_ID,emailIdString);
        contentValues.put(LoginEntry.COLUMN_PASSWORD,passwordString);
        contentValues.put(LoginEntry.COLUMN_POST,mPost);
        contentValues.put(LoginEntry.COLUMN_GENDER,mGender);

//        //Insert new row in Login database returning new row Id
//        long newRowId = db.insert(LoginEntry.TABLE_NAME,null,contentValues);
        Uri newUri = getContentResolver().insert(LoginEntry.CONTENT_URI,contentValues);
//        TextView displayTextView = (TextView) findViewById(R.id.textview_final_welcome);

//        Intent intent = new Intent("com.example.loginapp");
//        intent.putExtra("key",pass);
//     ``````````````````````````````````````````   startActivity(intent);
//        startActivity(new Intent(Account.this, Welcome.class).putExtra("key", pass));

        //check that the password contains at least 8 characters
        String nameFirst = contentValues.getAsString(LoginEntry.COLUMN_FIRST_NAME);
        String nameLast = contentValues.getAsString(LoginEntry.COLUMN_LAST_NAME);
        String phoneNumber = contentValues.getAsString(LoginEntry.COLUMN_PHONE_NUMBER);
        String password = contentValues.getAsString(LoginEntry.COLUMN_PASSWORD);
        String email = contentValues.getAsString(LoginEntry.COLUMN_EMAIL_ID);
//        String date = contentValues.getAsString(LoginEntry.COLUMN_DATE_OF_BIRTH);
//        int textSize = 14;

        if(nameFirst.length() == 0)
        {
            mFirstNameEditText.setError("ENTER FIRST NAME");
        }
        if(nameFirst.length() > 0 && !nameFirst.matches("[a-zA-Z]+"))
        {
            mFirstNameEditText.setError("ENTER VALID FIRST NAME");
        }

        if(nameLast.length() == 0)
        {
            mLastNameEditText.setError("ENTER LAST NAME");
        }
        if(nameLast.length() > 0 && !nameLast.matches("[a-zA-Z]+"))
        {
            mLastNameEditText.setError("ENTER VALID LAST NAME");
        }

        if(phoneNumber.length() == 0)
        {
            mPhoneNumberEditText.setError("ENTER MOBILE NUMBER");
        }
        if(phoneNumber.length() > 0 && phoneNumber.length() < 10)
        {
            mPhoneNumberEditText.setError("ENTER VALID MOBILE NUMBER");
        }
//
//        if (email.length() == 0)
//        {
//            mEmailIdEditText.setHint("ENTER EMAIL ADDRESS");
//            mEmailIdEditText.setTextSize(textSize);
//            mEmailIdEditText.setHintTextColor(Color.RED);
//        }
        if(email.length() == 0)
        {
            mEmailIdEditText.setError("ENTER EMAIL ID");
        }
        if (email.length() > 0 && !isValidEmail(email))
        {
            mEmailIdEditText.setError("ENTER VALID EMAIL ADDRESS");
//            mEmailIdEditText.setTextSize(textSize);
//            mEmailIdEditText.setHintTextColor(Color.RED);
        }


        if(password.length() == 0)
        {
            mPasswordEditText.setError("ENTER PASSWORD");
        }
        if(password.length() > 0 && password.length() < 8)
        {
            mPasswordEditText.setError("ENTER ATLEAST 8 CHARACTERS");
        }

//        if(date.equals("Date of Birth"))
//        {
//            mDateOfBirthTextView.setError("ENTER DATE OF BIRTH");
//        }


        //if newRowId is 1 then insert is succesfully done
        if(newUri == null)
        {
            Toast.makeText(this,"User Data not Save",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"User Data Save",Toast.LENGTH_SHORT).show();
        }
    }

//    private boolean isValidEmail(CharSequence email)
//    {
//        if (!TextUtils.isEmpty(email))
//        {
//            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
//        }
//        return false;
//    }

        private byte[] getBitmapArray(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public static boolean isValidEmail(CharSequence target)
    {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void setUpSpinnerPost()
    {
        List<String> spinnerElement = new ArrayList<>();

        //Add spinner Elements using add method in arraylist
//        spinnerElement.add(0,"Gender");
        spinnerElement.add("Lecturer");
        spinnerElement.add("Assistant Professor");
        spinnerElement.add("Associative Professor");

        //style and populate the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this,R.layout.spinner_item,spinnerElement);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        //attaching data adapter to spinner
        mPostSpinner.setAdapter(dataAdapter);

        mPostSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(parent.getItemAtPosition(position).equals("Lecturer"))
                {
                    mPost = LoginEntry.POST_LECTURER;
                }
                else if(parent.getItemAtPosition(position).equals("Assistant Professor"))
                {
                    mPost = LoginEntry.POST_ASSISTANT_PROFESSOR;
                }
                else
                {
                    mPost = LoginEntry.POST_ASSOCIATIVE_PROFESSOR;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void setUpSpinnerGender()
    {
        List<String> spinnerElement = new ArrayList<>();

        //Add spinner Elements using add method in arraylist
//        spinnerElement.add(0,"Gender");
        spinnerElement.add("Other");
        spinnerElement.add("Male");
        spinnerElement.add("Female");

        //style and populate the spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this,R.layout.spinner_item,spinnerElement);

        //Dropdown layout style
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);


        //attaching data adapter to spinner
        mGenderSpinner.setAdapter(dataAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(parent.getItemAtPosition(position).equals("Male"))
                {
                    mGender = LoginEntry.GENDER_MALE;
                }
                else if(parent.getItemAtPosition(position).equals("Female"))
                {
                   mGender = LoginEntry.GENDER_FEMALE;
                }
                else
                {
                    mGender = LoginEntry.GENDER_OTHER;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                mGender = LoginEntry.GENDER_OTHER;
            }
        });

    }

}
