package com.weebly.krishroy.mydatabasedemo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myProducts.db";
    public static final String TABLE_PRODUCTS = "myProducts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_STORENAME = "storename";

    public myDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCTNAME + " TEXT, " + COLUMN_STORENAME + " TEXT );";
        //String query = "CREATE TABLE " + TABLE_PRODUCTS + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCTNAME + " TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS + ";");
        onCreate(db);
    }

    //Add new row to table
    public boolean addProduct(Products p){
        SQLiteDatabase db = getWritableDatabase();

        String productName = p.get_productName();

        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME +"=\"" + productName + "\";";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        if(c.getCount()==0) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCTNAME, p.get_productName());
            values.put(COLUMN_STORENAME, p.getStoreName());

            db.insert(TABLE_PRODUCTS, null, values);
            db.close();
            return true;

        }
        else{
            db.close();
            return false;
        }


    }

    //Delete row from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
    }

    //toString method
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();

        //String query = "SELECT "+ COLUMN_PRODUCTNAME +" FROM " + TABLE_PRODUCTS + " WHERE 1;";
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1 GROUP BY productname;";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("productname")) != null) {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += " - ";
                dbString += c.getString(c.getColumnIndex("storename"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }
}
