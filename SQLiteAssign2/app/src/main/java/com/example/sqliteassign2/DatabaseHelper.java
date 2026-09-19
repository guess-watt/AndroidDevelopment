package com.example.sqliteassign2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LibraryDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "books";

    private static final String BOOK_ID = "bookId";
    private static final String BOOK_NAME = "bookName";
    private static final String AUTHOR_NAME = "authorName";
    private static final String PUBLISHER_NAME = "publisherName";
    private static final String YEAR = "year";
    private static final String PRICE = "price";
    private static final String STOCK = "stock";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                BOOK_ID + " INTEGER PRIMARY KEY, " +
                BOOK_NAME + " TEXT, " +
                AUTHOR_NAME + " TEXT, " +
                PUBLISHER_NAME + " TEXT, " +
                YEAR + " INTEGER, " +
                PRICE + " REAL, " +
                STOCK + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public boolean insertBook(int bookId, String bookName,
                              String authorName, String publisherName,
                              int year, double price, int stock) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BOOK_ID, bookId);
        values.put(BOOK_NAME, bookName);
        values.put(AUTHOR_NAME, authorName);
        values.put(PUBLISHER_NAME, publisherName);
        values.put(YEAR, year);
        values.put(PRICE, price);
        values.put(STOCK, stock);

        long result = db.insert(TABLE_NAME, null, values);

        return result != -1;
    }

    public Cursor getAllBooks() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLE_NAME,
                null
        );
    }
}