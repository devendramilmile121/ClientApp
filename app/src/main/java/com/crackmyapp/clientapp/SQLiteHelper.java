package com.crackmyapp.clientapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dhmil on 13-02-2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="DataBase";
    public static final String KEY_ID="id";
    public static final String TABLE_NAME="offerTable";
    public static final String KEY_Image="imagepath";
    public static final String KEY_Offer="offername";
    public static final String KEY_Offer_Details="offerdetails";
    public static final String KEY_Actual_Price="actualprice";
    public static final String KEY_Discount="discount";
    public static final String KEY_Discount_Price="discountprice";


    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Image+" VARCHAR, "+KEY_Offer+" VARCHAR, "+KEY_Offer_Details+" VARCHAR,"+KEY_Actual_Price+" VARCHAR,"+KEY_Discount+" VARCHAR,"+KEY_Discount_Price+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}
