package com.crackmyapp.clientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dhmil on 13-02-2018.
 */

public class SQLiteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> userID;
    ArrayList<String> UserOffer;
    ArrayList<String> UserOfferDetails;
    ArrayList<String> UserActualPrice ;
    ArrayList<String> UserDiscount ;
    ArrayList<String> UserDiscountPrice ;
    ArrayList<String> userImage;

    public SQLiteListAdapter(
            Context context2,

            ArrayList<String> userid,
            ArrayList<String> offer,
            ArrayList<String> offerdetails,
            ArrayList<String> actualprice,
            ArrayList<String> discount,
            ArrayList<String> discountprice,
            ArrayList<String> image_path_array
            )

    {
        this.context = context2;
        this.userID = userid;
        this.UserOffer = offer;
        this.UserOfferDetails= offerdetails;
        this.UserActualPrice= actualprice;
        this.UserDiscount = discount ;
        this.UserDiscountPrice = discountprice;
        this.userImage = image_path_array;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.list_view_data, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.offer_name = (TextView) child.findViewById(R.id.offer_name);
            holder.offer_detail = (TextView) child.findViewById(R.id.offer_detail);
            holder.actualprice = (TextView) child.findViewById(R.id.actualprice);
            holder.discount = (TextView) child.findViewById(R.id.discount);
            holder.discount_price = (TextView) child.findViewById(R.id.discount_price);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.offer_name.setText(UserOffer.get(position));
        holder.offer_detail.setText(UserOfferDetails.get(position));
        holder.actualprice.setText(UserActualPrice.get(position));
        holder.discount.setText(UserDiscount.get(position));
        holder.discount_price.setText(UserDiscountPrice.get(position));

        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView offer_name;
        TextView offer_detail;
        TextView actualprice;
        TextView discount;
        TextView discount_price;
    }
}
