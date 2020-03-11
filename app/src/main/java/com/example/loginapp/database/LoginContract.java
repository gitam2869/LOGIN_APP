package com.example.loginapp.database;


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the Login App
 */
public final class LoginContract
{
    //To prevent someone from accidentally instatiating the contract class,
    //make the constructor private
    private LoginContract()
    {
      //do nothing here
    }

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public final static String CONTENT_AUTHORITY = "com.example.loginapp";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_LOGIGN = "log";

    /**
     * Inner class that defines constant values for the Login database table.
     * Each Entry in the table represents a single user info
     */
    public static final class LoginEntry implements BaseColumns
    {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI =  Uri.withAppendedPath(BASE_CONTENT_URI,PATH_LOGIGN);
        /**
         * Name of database table for Login App
         */
        public static final String TABLE_NAME = "log";

        /**
         * dummy table
         */

        public static final String TABLE_NAME_STUDENT = "dummy";
        /**
         * Unique ID number for user (only for use in the database table)
         *
         * Type : INTEGER
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * First Name of User
         *
         * Type: TEXT
         */
        public static final String COLUMN_FIRST_NAME = "first_name";

        /**
         * Last Name of User
         *
         * TYPE: TEXT
         */
        public static final String COLUMN_LAST_NAME = "last_name";

        /**
         * Phone Number of User
         *
         * Type: INTEGER
         */
        public static final String COLUMN_PHONE_NUMBER = "phone_number";

        /**
         * Email-ID of User
         *
         * Type: TEXT
         */
        public static final String COLUMN_EMAIL_ID = "email_id";

        /**
         * Password of User
         *
         * Type: TEXT
         */
        public static final String COLUMN_PASSWORD = "password";

        /**
         * Date of Birth of User
         *
         * Type:BLOB
         */
        public static final String COLUMN_POST = "post";

        public static final int POST_LECTURER = 0;
        public static final int POST_ASSISTANT_PROFESSOR = 1;
        public static final int POST_ASSOCIATIVE_PROFESSOR = 2;

        /**
         * Gender of User
         *
         * Type: INTGER
         */
        public static final String COLUMN_GENDER = "gender";

        /**
         * possible values for the gender of the User.
         */
        public static final int GENDER_OTHER = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

    }
}
