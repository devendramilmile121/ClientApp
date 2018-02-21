package com.crackmyapp.clientapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by dhmil on 09-02-2018.
 */

public class GetData extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Getting data from broadcast

        String imagepath =intent.getExtras().getString("Image_path");
        String offername = intent.getExtras().getString("Offer_Name");
        String offerdetails = intent.getExtras().getString("Offer_Details");
        String actualprice = intent.getExtras().getString("Actual_Price");
        String discount = intent.getExtras().getString("Discount");
        String discountprice = intent.getExtras().getString("Discount_Price");
        Notification(context,imagepath,offername,offerdetails,actualprice,discount,discountprice);

    }

    public void Notification(Context context, String imagepath,String offername,String offerdetails,String actualprice, String discount, String discountprice){

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("Image_path",imagepath);
        intent.putExtra("Offer_Name",offername);
        intent.putExtra("Offer_Details",offerdetails);
        intent.putExtra("Actual_Price",actualprice);
        intent.putExtra("Discount",discount);
        intent.putExtra("Discount_Price",discountprice);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder noti = new NotificationCompat.Builder(context);

        noti.setSmallIcon(R.drawable.ic_announcement);
        noti.setContentTitle("New offer received");
        noti.setContentText(offername);
        noti.setContentIntent(pIntent);
        noti.setAutoCancel(true);
        noti.setSound(soundUri);
        mNotificationManager.notify(0,noti.build());
    }
}
