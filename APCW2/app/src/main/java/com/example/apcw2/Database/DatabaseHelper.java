package com.example.apcw2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.apcw2.Model.APModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper{

    // Database constants
    private static final String DATABASE_NAME = "contact_details";
    public static final String TABLE_ACCOUNT = "Accounts";
    private static final String ID_COLUMN = "ID";
    private static final String NAME_COLUMN = "Name";
    private static final String DOB_COLUMN = "DOB";
    private static final String EMAIL_COLUMN = "Email";
    private  static  final  String IMAGE_COLUMN = "AccountImage";

    // Image storage variables
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;

    // Context reference for Toast messages
    private final Context context;

    // SQL query for creating the Accounts table
    private String Account = "CREATE TABLE " + TABLE_ACCOUNT + " (" +
            ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_COLUMN + " nvarchar," +
            DOB_COLUMN + " nvarchar, " +
            EMAIL_COLUMN + " nvarchar, " +
            IMAGE_COLUMN + " BLOB ); ";

    // Constructor
    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    // Method called when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Account);
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        Log.w(this.getClass().getName(), DATABASE_NAME +
                " database upgrade to version "
                + newVersion + " - old data lost");
        onCreate(db);
    }

    // Method to add a new account to the database
    public void addAccount(String name, String dob, String email, Bitmap accountImage){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Convert Bitmap to byte array for storage
        Bitmap imageToStorageBitmap = accountImage;
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStorageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInByte = byteArrayOutputStream.toByteArray();

        // Put values into ContentValues
        contentValues.put(NAME_COLUMN, name);
        contentValues.put(DOB_COLUMN, dob);
        contentValues.put(EMAIL_COLUMN, email);
        contentValues.put("AccountImage", imageInByte);

        // Insert the values into the Accounts table
        long result = db.insert(TABLE_ACCOUNT, null, contentValues);

        // Show success or failure message using Toast
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
        }
    }

    // Method to fetch account data from the database
    public ArrayList<APModel> fetchAccountData() {
        ArrayList<APModel> account = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACCOUNT  + " ORDER BY " + ID_COLUMN + " DESC", null);

        // Iterate through the cursor and create AccountModel objects
        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String email = cursor.getString(3);
                byte[] image = cursor.getBlob(4);
                Bitmap objectBitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                APModel accountModel = new APModel(id, name, date, email, objectBitmap);
                account.add(accountModel);
            } while (cursor.moveToNext());
        }

        return account;
    }
}
