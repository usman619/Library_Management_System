package com.example.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

public class IssueBookDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "LMS.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "IssueBook";
    SQLiteDatabase db;

    public IssueBookDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
//        DB.execSQL("CREATE TABLE " + TABLE_NAME + " (bookID TEXT primary key, rollNo TEXT, dueDate TEXT)");
        DB.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, bookID TEXT, rollNo TEXT, dueDate TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertIssueBook(String bookId, String rollNo, String dueDate){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("bookID", bookId);
        contentValues.put("rollNo", rollNo);
        contentValues.put("dueDate", dueDate); // Insert the Date object directly
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result != -1){
            return true;
        }
        else{
            return false;
        }
    }


//    public boolean deleteIssueBook(String ID){
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{ID});
//        if(cursor.getCount() > 0){
//            long result = db.delete(TABLE_NAME, "bookId=?", new String[]{ID});
//            return result != -1;
//        } else {
//            return false;
//        }
//    }

//    public boolean deleteIssueBook(String rollno,String ID){
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ? and rollNo= ?", new String[]{rollno,ID});
//        if(cursor.getCount() > 0){
//            long result = db.delete(TABLE_NAME, "bookId=? and rollNo=?", new String[]{rollno,ID});
//            return result != -1;
//        } else {
//            return false;
//        }
//    }

    public boolean deleteIssueBook(String rollno, String ID) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ? and rollNo= ?", new String[]{ID, rollno});
        if (cursor.getCount() > 0) {
            long result = db.delete(TABLE_NAME, "ID=? and rollNo=?", new String[]{ID, rollno});
            return result != -1;
        } else {
            return false;
        }
    }


    public Cursor getAllIssuedBook(String rollNo){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT bookID FROM " + TABLE_NAME + " where rollNo = ?", new String[]{rollNo});
        return cursor;
    }


}
