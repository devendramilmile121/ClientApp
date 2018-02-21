package com.crackmyapp.clientapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class OfferPurchase extends AppCompatActivity {
    TextView tv_id;
    TextView tv_offername;
    TextView tv_offerdetails;
    TextView tv_actualprice;
    TextView tv_discount;
    TextView tv_discountprice;

    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
       Bundle data = getIntent().getExtras();

        String id = data.getString("Product_Id");
        String imagepath = data.getString("Image_path");
        String offername = data.getString("Offer_Name");
        String offerdetails = data.getString("Offer_Details");
        String actualprice = data.getString("Actual_Price");
        String discount = data.getString("Discount");
        String discountprice = data.getString("Discount_Price");

        tv_id = (TextView)findViewById(R.id.textViewID);
        tv_offername = (TextView)findViewById(R.id.offer_name);
        tv_offerdetails = (TextView)findViewById(R.id.offer_detail);
        tv_actualprice =  (TextView)findViewById(R.id.actualprice);
        tv_discount = (TextView)findViewById(R.id.discount);
        tv_discountprice= (TextView)findViewById(R.id.discount_price);

        img = (ImageView)findViewById(R.id.imageView);

        tv_id.setText(id);
       tv_offername.setText(offername);
       tv_offerdetails.setText(offerdetails);
       tv_actualprice.setText(actualprice);
       tv_discount.setText(discount);
       tv_discountprice.setText(discountprice);

       File image = new File(imagepath);
       if (image.exists()){
       Bitmap myBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
       img.setImageBitmap(myBitmap);}else {
           Toast.makeText(getApplicationContext(),"File Not Exists !",Toast.LENGTH_LONG).show();
       }

    }

    public void showPurchase(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to purchase this product ?");
        builder.setTitle("Purchase Product");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Successfully Purchased !",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Product Purchase");
        alertDialog.show();
    }


}
