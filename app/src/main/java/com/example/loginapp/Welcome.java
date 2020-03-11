package com.example.loginapp;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.loginapp.database.LoginProvider;
import com.example.loginapp.database.LoginDbHelper;

import com.example.loginapp.database.LoginContract.LoginEntry;

public class Welcome extends AppCompatActivity
{
    private LoginDbHelper mLoginDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        Intent intent = getIntent();
        String value = intent.getStringExtra("key");
//        Bundle bundle = getIntent().getExtras();
//
//
//             String value = bundle.getString("key");
//
//
        TextView display = (TextView) findViewById(R.id.textview_final_welcome);
        display.setText(value);
        mLoginDbHelper = new LoginDbHelper(this);
    }


    private void displayDatabaseInfo()
    {
        //To access our database, we instantiate our subclass of SQLiteOpenHelper
        //and pass the context, which is the current activity
//        LoginDbHelper mLoginDbHelper = new LoginDbHelper(this);

        //create and / or open a database to read from it
        SQLiteDatabase db = mLoginDbHelper.getReadableDatabase();

        //definen a projection that specifies which columns from the database
        //you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                LoginEntry.COLUMN_FIRST_NAME,
                LoginEntry.COLUMN_LAST_NAME,
                LoginEntry.COLUMN_PHONE_NUMBER,
                LoginEntry.COLUMN_EMAIL_ID,
                LoginEntry.COLUMN_PASSWORD,
                LoginEntry.COLUMN_POST,
                LoginEntry.COLUMN_GENDER
        };

        Cursor cursor = db.query(
                LoginEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

//        //perform this raw SQl query "SELECT *FROM loginuserdata"
//        //to get a cursor that contains all rows from the loginuserdata
//        Cursor cursor = db.rawQuery("SELECT *FROM " + LoginEntry.TABLE_NAME,null);

        TextView displayTextView = (TextView) findViewById(R.id.textview_welcome);

        try
        {
            //Display the number of rows in the table
            displayTextView.setText("Number of User : " + cursor.getCount()+" in Login App\n\n");
            displayTextView.append(LoginEntry._ID
                    +"    "+LoginEntry.COLUMN_FIRST_NAME
                    +"    "+LoginEntry.COLUMN_LAST_NAME+"\n");

            //find index of columns
            int idColumnIndex = cursor.getColumnIndex(LoginEntry._ID);
            int firstNameColumnIndex = cursor.getColumnIndex(LoginEntry.COLUMN_FIRST_NAME);
            int lastNameColumlnIndex = cursor.getColumnIndex(LoginEntry.COLUMN_LAST_NAME);

            while (cursor.moveToNext())
            {
                int currentId = cursor.getInt(idColumnIndex);
                String currentFirstName = cursor.getString(firstNameColumnIndex);
                String currentLastName = cursor.getString(lastNameColumlnIndex);
                displayTextView.append("\n"+currentId
                        +"    "+currentFirstName
                        +"    "+currentLastName);
            }

        }
        finally
        {
            cursor.close();
        }
    }


}