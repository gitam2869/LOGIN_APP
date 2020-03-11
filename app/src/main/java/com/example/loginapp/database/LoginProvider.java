package com.example.loginapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.example.loginapp.R;
import com.example.loginapp.database.LoginContract.LoginEntry;

/**
 * {@link ContentProvider} for Pets app.
 */
public class LoginProvider extends ContentProvider
{

    private LoginDbHelper mLoginDbHelper;

    /** Tag for the log messages */
    public static final String LOG_TAG = LoginProvider.class.getSimpleName();

    /** URI matcher code for the content URI for the log table */
    private static final int LOGS = 100;

    /** URI matcher code for the content URI for a single log in the final table*/
    private static final int LOG_ID = 101;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    /** Static initializer .this is run the first time anything is called from this class*/
    static
    {
        //The calls to addURI() go here , for all of the content URI patterns that the provider
        //should recognize.all paths added to the UriMatcher have a corresponding code to return
        //when a match is found.

        //UriMatcher for all table
        sUriMatcher.addURI(LoginContract.CONTENT_AUTHORITY,LoginContract.PATH_LOGIGN,LOGS);

        //UriMatcher for specific row
        sUriMatcher.addURI(LoginContract.CONTENT_AUTHORITY,LoginContract.PATH_LOGIGN+"/#",LOG_ID);
    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate()
    {
        // TODO: Create and initialize a LoginDbHelper object to gain access to the final database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.

        mLoginDbHelper = new LoginDbHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        //Get readable database
        SQLiteDatabase db = mLoginDbHelper.getReadableDatabase();

        //This cursor will hold the result of the query
        Cursor cursor;

        //figure out if the URI matcher can match the URI to a specific code
        int match = sUriMatcher.match(uri);

        switch (match)
        {
            case LOGS:
                // For the LOGS code, query the final table directly with the given
                // projection, selection, selection arguments, and sort order. The cursor
                // could contain multiple rows of the final table.
                // TODO: Perform database query on pets table
                cursor = db.query(LoginEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);

                break;

            case LOG_ID:
                // For the LOG_ID code, extract out the ID from the URI.
                // For an example URI such as "content://com.example.loginapp/final/3",
                // the selection will be "_id=?" and the selection argument will be a
                // String array containing the actual ID of 3 in this case.
                //
                // For every "?" in the selection, we need to have an element in the selection
                // arguments that will fill in the "?". Since we have 1 question mark in the
                // selection, we have 1 String in the selection arguments' String array.

                selection = LoginContract.LoginEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                // This will perform a query on the final table where the _id equals 3 to return a
                // Cursor containing that row of the table.
                cursor = db.query(LoginEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues)
    {
        //figure out if the URI matcher can match the URI to a specific code
        final int match = sUriMatcher.match(uri);

        switch (match)
        {
            case LOGS:
                return insertUserData(uri,contentValues);

            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertUserData(Uri uri,ContentValues contentValues)
    {
//        //check that the first name is not null
//        String firstName = contentValues.getAsString(LoginEntry.COLUMN_FIRST_NAME);
//        if(firstName == null)
//        {
//            throw new IllegalArgumentException("App requires First Name");
//        }
//
//        //check that the second name is not null
//        String lastName = contentValues.getAsString(LoginEntry.COLUMN_LAST_NAME);
//        if(lastName == null)
//        {
//            throw new IllegalArgumentException("App requires Last Name");
//        }




        //get database object
        SQLiteDatabase db = mLoginDbHelper.getWritableDatabase();

        //get id of current row
        long id = db.insert(LoginEntry.TABLE_NAME,null,contentValues);

        if(id == -1)
        {
            Log.e(LOG_TAG,"Failed to insert row for "+uri);
            return null;
        }

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }


    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs)
    {
        return 0;
    }

    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri)
    {
        return null;
    }
}