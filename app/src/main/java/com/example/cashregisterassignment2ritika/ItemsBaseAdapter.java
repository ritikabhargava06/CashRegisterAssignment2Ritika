package com.example.cashregisterassignment2ritika;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ItemsBaseAdapter extends BaseAdapter {

    ArrayList<Item> itemsList;

    public ItemsBaseAdapter(ArrayList<Item> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    Context context;

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemRowView = LayoutInflater.from(context).inflate(R.layout.items_row,parent,false);
        TextView itemName = itemRowView.findViewById(R.id.itemRowName);
        TextView itemPrice = itemRowView.findViewById(R.id.itemRowPrice);
        TextView itemQuantity = itemRowView.findViewById(R.id.itemRowQuantity);
        itemName.setText(itemsList.get(position).getItemName());
        itemPrice.setText(String.valueOf(itemsList.get(position).getItemPrice()));
        itemQuantity.setText(String.valueOf(itemsList.get(position).getItemQuantity()));

        return itemRowView;
    }
}
