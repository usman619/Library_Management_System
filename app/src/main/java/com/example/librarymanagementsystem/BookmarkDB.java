package com.example.librarymanagementsystem;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookmarkDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "LMS.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Bookmark";
    SQLiteDatabase db;

    public BookmarkDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, bookID TEXT, rollNo TEXT, soldout BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertBookmark(String bookId, String rollNo, boolean soldout){
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", bookId);
        contentValues.put("rollNo", rollNo);
        contentValues.put("soldout", soldout);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean deleteBookmark(String ID){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID = ?", new String[]{ID});
        if(cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "bookId=?", new String[]{ID});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getAllBookmarks(String rollNo){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where rollNo = ?", new String[]{rollNo});
        return cursor;
    }


}