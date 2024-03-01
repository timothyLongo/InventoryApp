package com.example.longo_cs_360_5_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    DBHelper(Context context) {

        super(context, "Userdata.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create Table UserInventory(name TEXT primary key," +
                "price TEXT, quantity TEXT)");

        db.execSQL("create Table Users(username TEXT primary key," +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserInventory");
        db.execSQL("drop Table if exists Users");
        onCreate(db);
    }


    public Boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        long result = db.insert("Users", null, cv);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }



    public Boolean checkUser(String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Users where username = ?",
                new String[] {user});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkUserPass(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Users where username = ?" +
                        " and password = ?",
                new String[] {user, pass});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }





    public Boolean insertData(String name, String price, String quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        contentValues.put("quantity", quantity);
        long result = db.insert("UserInventory", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }


    public Boolean delData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserInventory where name = ?",
                new String[] {name});
        if (cursor.getCount()>0) {

            long result = db.delete("UserInventory", "name=?",
                    new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }


    public Cursor readAllData() {
        String query = "SELECT * FROM UserInventory";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String name, String price, String quantity) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("price", price);
        cv.put("quantity", quantity);

        long result = db.update("UserInventory", cv,
                "name=?", new String[]{name});

    }


}
