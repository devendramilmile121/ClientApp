package com.crackmyapp.clientapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    String imagepath;
    String offername;
    String offerdetails;
    String actualprice;
    String discount;
    String discountprice;

    String SQLiteQuery ;

    SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
    ListView LISTVIEW;
    ArrayList<String> id_array = new ArrayList<String>();
    ArrayList<String> imagepath_array= new ArrayList<String>();
    ArrayList<String> offer_array= new ArrayList<String>();
    ArrayList<String> offerdetails_array= new ArrayList<String>();
    ArrayList<String> actualprice_array = new ArrayList<String>();
    ArrayList<String> discount_array = new ArrayList<String>();
    ArrayList<String> discountprice_array = new ArrayList<String>();

    private static final int REQUEST_RESPONSE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LISTVIEW = (ListView) findViewById(R.id.lvData);
        SQLITEHELPER = new SQLiteHelper(this);
        ShowSQLiteDBdata() ;
        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationmanager.cancel(0);



        Intent i = getIntent();
        imagepath = i.getStringExtra("Image_path");
        offername = i.getStringExtra("Offer_Name");
        offerdetails = i.getStringExtra("Offer_Details");
        actualprice = i.getStringExtra("Actual_Price");
        discount = i.getStringExtra("Discount");
        discountprice = i.getStringExtra("Discount_Price");

        DBCreate();
        SubmitData2SQLiteDB();

    }


    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS offerTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,imagepath VARCHAR, offername VARCHAR, offerdetails VARCHAR, actualprice VARCHAR,discount VARCHAR, discountprice VARCHAR);");
    }

    public void SubmitData2SQLiteDB(){
        if (imagepath!=null&&offername!=null&&offerdetails!=null&&actualprice!=null&&discount!=null&&discountprice!=null)
        {
            SQLiteQuery = "INSERT INTO offerTable (imagepath,offername,offerdetails,actualprice,discount,discountprice) VALUES('" + imagepath + "', '" + offername + "', '" + offerdetails + "' , '" + actualprice + "','" + discount + "','" + discountprice + "');";
            SQLITEDATABASE.execSQL(SQLiteQuery);
        }
    }




    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM offerTable", null);

        id_array.clear();
        imagepath_array.clear();
        offer_array.clear();
        offerdetails_array.clear();
        actualprice_array.clear();
        discount_array.clear();
        discountprice_array.clear();

        if (cursor.moveToFirst()) {
            do {
                id_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));

                imagepath_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Image)));

                offer_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Offer)));

                offerdetails_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Offer_Details)));

                actualprice_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Actual_Price)));

                discount_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Discount)));

                discountprice_array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_Discount_Price)));

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(MainActivity.this,

                id_array,
                offer_array,
                offerdetails_array,
                actualprice_array,
                discount_array,
                discountprice_array,
                imagepath_array


        );

        LISTVIEW.setAdapter(ListAdapter);
        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id = id_array.get(i);
                String imagepath1 = imagepath_array.get(i);
                String offername1 = offer_array.get(i);
                String offerdetails1 = offerdetails_array.get(i);
                String actualprice1 = actualprice_array.get(i);
                String discount1 = discount_array.get(i);
                String discountprice1 = discountprice_array.get(i);
                Intent show = new Intent(MainActivity.this,OfferPurchase.class);
                show.putExtra("Product_Id",id);
                show.putExtra("Image_path",imagepath1);
                show.putExtra("Offer_Name",offername1);
                show.putExtra("Offer_Details",offerdetails1);
                show.putExtra("Actual_Price",actualprice1);
                show.putExtra("Discount",discount1);
                show.putExtra("Discount_Price",discountprice1);
                startActivityForResult(show,REQUEST_RESPONSE);
            }
        });

        cursor.close();
    }


    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

}
