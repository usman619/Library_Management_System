package com.example.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BooksDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "LMS.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Books";
    SQLiteDatabase db;

    public BooksDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE " + TABLE_NAME + " (bookId TEXT PRIMARY KEY, title TEXT, author TEXT, genre TEXT, quantity INTEGER, soldout BOOLEAN)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertBook(String bookId, String title, String author, String genre, int quantity, boolean soldout){
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", bookId);
        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("genre", genre);
        contentValues.put("quantity", quantity);
        contentValues.put("soldout", soldout);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean updateBook(String bookId, String title, String author, String genre, int quantity, boolean soldout){
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("author", author);
        contentValues.put("genre", genre);
        contentValues.put("quantity", quantity);
        contentValues.put("soldout", soldout);

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE bookId = ?", new String[]{bookId});
        if(cursor.getCount() > 0){
            long result = db.update(TABLE_NAME, contentValues, "bookId=?", new String[]{bookId});
            return result != -1;
        } else {
            return false;
        }
    }

    public boolean deleteBook(String bookId){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE bookId = ?", new String[]{bookId});
        if(cursor.getCount() > 0){
            long result = db.delete(TABLE_NAME, "bookId=?", new String[]{bookId});
            return result != -1;
        } else {
            return false;
        }
    }

    public Cursor getAllBooks(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }

    public Cursor getBook(String bookId){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where bookID = ?", new String[]{bookId});
        return cursor;
    }

}
