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
        DB.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, bookID TEXT, rollNo TEXT, dueDate DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertIssueBook(String bookId, String rollNo, Date dueDate){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", bookId);
        contentValues.put("rollNo", rollNo);
        contentValues.put("dueDate", String.valueOf(dueDate));
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result != -1){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean deleteIssueBook(String ID){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{ID});
        if(cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "bookId=?", new String[]{ID});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getAllIssuedBook(String rollNo){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where rollNo = ?", new String[]{rollNo});
        return cursor;
    }


}
