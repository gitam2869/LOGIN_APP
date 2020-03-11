package com.example.loginapp;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.loginapp.database.LoginContract.LoginEntry;


import com.example.loginapp.database.LoginContract;
import com.example.loginapp.database.LoginDbHelper;

public class MainActivity extends AppCompatActivity
{
    private LoginDbHelper mLoginDbHelper;

//    private DatePickerDialog.OnDateSetListener mDateSetListener;
//    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView newAccountTextView = (TextView) findViewById(R.id.textview_create_new_account_sign_in);
//        TextView forgetTextView = (TextView) findViewById(R.id.textView_forgot_passowrd);
//        Button loginButton = (Button) findViewById(R.id.button_login);

        newAccountTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this,Account.class);
                startActivity(i);
//                Toast.makeText(v.getContext(),"intent",Toast.LENGTH_SHORT).show();
            }
        });

        mLoginDbHelper = new LoginDbHelper(this);
//        displayDatabaseInfo();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo()
    {
        //To access our database, we instantiate our subclass of SQLiteOpenHelper
        //and pass the context, which is the current activity
//        LoginDbHelper mLoginDbHelper = new LoginDbHelper(this);

        //create and / or open a database to read from it
//        SQLiteDatabase db = mLoginDbHelper.getReadableDatabase();

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

//        Cursor cursor = db.query(
//                LoginEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null
//                );

        Cursor cursor = getContentResolver().query(
                LoginEntry.CONTENT_URI,
                projection,null,
                null,
                null);


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

//            while (cursor.moveToNext())
//            {
//                int currentId = cursor.getInt(idColumnIndex);
//                String currentFirstName = cursor.getString(firstNameColumnIndex);
//                String currentLastName = cursor.getString(lastNameColumlnIndex);
//                displayTextView.append("\n"+currentId
//                        +"    "+currentFirstName
//                        +"    "+currentLastName);
//            }

        }
        finally
        {
            cursor.close();
        }
    }

    private void insertLoginData()
    {
//        LoginDbHelper mLoginDbHelper = new LoginDbHelper(this);
//
//        //get the database in write mode
//        SQLiteDatabase db = mLoginDbHelper.getWritableDatabase();

        //create ContentValues object where column names are the keys ,
        //use information attributes are values
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoginEntry.COLUMN_FIRST_NAME,"Gautam");
        contentValues.put(LoginEntry.COLUMN_LAST_NAME,"Helange");
        contentValues.put(LoginEntry.COLUMN_PHONE_NUMBER,"7517424135");
        contentValues.put(LoginEntry.COLUMN_EMAIL_ID,"gautamhelange025@gmail.com");
        contentValues.put(LoginEntry.COLUMN_PASSWORD,"gautam@123");
        contentValues.put(LoginEntry.COLUMN_POST,"17/02/1999");
        contentValues.put(LoginEntry.COLUMN_GENDER,LoginEntry.GENDER_MALE);

//        //Insert new row in database, returning the primary key value of the new row
//        long newRowId = db.insert(LoginEntry.TABLE_NAME,null,contentValues);
//        Log.v("Welcome","New Row Id : "+newRowId);

        Uri newUri = getContentResolver().insert(LoginEntry.CONTENT_URI,contentValues);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //this adds the menu at app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_insert,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        //user click on one of the following options
        switch(menuItem.getItemId())
        {
            case R.id.menu_data:
                insertLoginData();
                displayDatabaseInfo();
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
