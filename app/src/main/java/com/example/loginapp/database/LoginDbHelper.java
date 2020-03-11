package com.example.loginapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.loginapp.database.LoginContract.LoginEntry;

public class LoginDbHelper extends SQLiteOpenHelper
{
    private LoginDbHelper mLoginDbHelper;

    /**
     * Name of the database file
     */
    public static final String DATABASE_NAME = "final.db";

    /**
     * Database version. If you change the database schema, you must increment the version.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     *create constructor of LoginDbHelper
     *
     * @param context
     */
    public LoginDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    /**
     */
    /**
     *This method is called when the database is created for first time
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create a string that contains the SQL statements to create the LoginData Table

        String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + LoginEntry.TABLE_NAME + "("
                + LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LoginEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_PHONE_NUMBER + " TEXT,"
                + LoginEntry.COLUMN_EMAIL_ID + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_PASSWORD + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_POST + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_GENDER + " INTEGER NOT NULL)";

        String SQL_CREATE_LOGIN_TABLE_STUDENT = "CREATE TABLE " + LoginEntry.TABLE_NAME_STUDENT + "("
                + LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LoginEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_LAST_NAME + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_PHONE_NUMBER + " TEXT,"
                + LoginEntry.COLUMN_EMAIL_ID + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_PASSWORD + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_POST + " TEXT NOT NULL,"
                + LoginEntry.COLUMN_GENDER + " INTEGER NOT NULL)";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);
        db.execSQL(SQL_CREATE_LOGIN_TABLE_STUDENT);
    }

    /**
     * This method is called when the database needs upgraded
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
