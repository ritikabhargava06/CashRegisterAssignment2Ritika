package com.example.cashregisterassignment2ritika;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsBaseAdapter extends BaseAdapter {

    ArrayList<Item> itemsList;
    ArrayList<PurchaseHistory> historyList;

    public ItemsBaseAdapter(ArrayList<PurchaseHistory> historyList, Context context, boolean flag) {
        this.historyList = historyList;
        this.context = context;
    }

    public ItemsBaseAdapter(ArrayList<Item> itemsList, Context context) {
        this.itemsList = itemsList;
        this.context = context;
    }

    Context context;

    @Override
    public int getCount() {
        int count=0;
        if( context.getClass() == MainActivity.class){
            count= itemsList.size();
        }else if (context.getClass() == HistoryListActivity.class){
            count = historyList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        if( context.getClass() == MainActivity.class){
            return itemsList.get(position);
        }else if (context.getClass() == HistoryListActivity.class){
            return historyList.get(position);
        }else return null;

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemRowView = LayoutInflater.from(context).inflate(R.layout.items_row,parent,false);
        TextView itemName = itemRowView.findViewById(R.id.itemRowName);
        TextView itemPriceOrQuantity = itemRowView.findViewById(R.id.itemRowPriceORQuantity);
        TextView itemQuantityOrTotal = itemRowView.findViewById(R.id.itemRowQuantityOrTotal);

       if( context.getClass() == MainActivity.class){
           itemName.setText(itemsList.get(position).getItemName());
           itemPriceOrQuantity.setText(String.valueOf(itemsList.get(position).getItemPrice()));
           itemQuantityOrTotal.setText(String.valueOf(itemsList.get(position).getItemQuantity()));
       }else if (context.getClass() == HistoryListActivity.class){
           itemName.setText(historyList.get(position).getPurchasedItemName());
           itemPriceOrQuantity.setText(String.valueOf(historyList.get(position).getPurchasedItemQuantity()));
           itemQuantityOrTotal.setText(String.valueOf(historyList.get(position).getPurchasedTotalPrice()));
       }
        return itemRowView;
    }
}
