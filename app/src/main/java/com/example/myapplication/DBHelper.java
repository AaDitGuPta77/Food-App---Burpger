package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String  DBNAME = "login.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE users(unsername TEXT primary key,password TEXT,fullName TEXT,email TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username, String password, String fullName, String email,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("unsername",username);
        contentValues.put("password",password);
        contentValues.put("fullName",fullName);
        contentValues.put("email",email);
        contentValues.put("phone",phone);

        long results = db.insert("users",null,contentValues);
        if (results == -1)
            return false;
        else
            return true;

    }

    public  boolean checkUsername (String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM users WHERE unsername = ?",new  String[] {username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkusernamePassword (String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery("SELECT * FROM users WHERE unsername = ? and password = ?",new  String[] {username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean changePassword (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("UPDATE users SET password = ? WHERE unsername = ?",new String[] {password,username});
        if  (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkCredantials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from users WHERE unsername = ? AND password = ?",new String[] {username,password});
        if (cursor.getCount() > 0) {
            return true;

        }else {
            return false;
        }

    }



}
