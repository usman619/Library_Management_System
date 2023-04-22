package com.example.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "LMS.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Student";
    SQLiteDatabase db;

    public StudentDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create table "+TABLE_NAME+" (rollNo TEXT primary key, name TEXT, contactNo TEXT, dept TEXT, password TEXT, bookIssued INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertstudent(String rollNo, String name, String contactNo, String dept, String password, int bookIssued){
        ContentValues contentValues = new ContentValues();
        contentValues.put("rollNo", rollNo);
        contentValues.put("name", name);
        contentValues.put("contactNo", contactNo);
        contentValues.put("dept", dept);
        contentValues.put("password", password);
        contentValues.put("bookIssued", bookIssued);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean updatestudent(String rollNo, String name, String contactNo, String dept, String password, int bookIssued){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contactNo", contactNo);
        contentValues.put("dept", dept);
        contentValues.put("password", password);
        contentValues.put("bookIssued", bookIssued);
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where rollNo = ?", new String[]{rollNo});
        if(cursor.getCount() > 0){
            long result = db.update(TABLE_NAME, contentValues, "rollNo=?", new String[]{rollNo});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
            return false;
    }

    public boolean deletestudent(String rollNo){
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME+" where rollNo = ?", new String[]{rollNo});
        if(cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "rollNo=?", new String[]{rollNo});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
            return false;
    }

    public Cursor getAllstudents(){
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME, null);
        return cursor;
    }

    public Cursor getstudent(String rollNo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select name,contactNo,dept from " +TABLE_NAME+ " where rollNo = ?", new String[]{String.valueOf(rollNo)});
        return cursor;
    }

    public Cursor getpassword(String rollNo){
        Cursor cursor = db.rawQuery("Select password from "+TABLE_NAME+" where rollNo = ?", new String[]{rollNo});
        return cursor;
    }

}