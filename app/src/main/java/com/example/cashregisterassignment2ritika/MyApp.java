package com.example.cashregisterassignment2ritika;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    private ArrayList<Item> itemsArrayList;
    private ArrayList<PurchaseHistory> purchaseHistoryArrayList = new ArrayList<>();

    public ArrayList<PurchaseHistory> getPurchaseHistoryArrayList() {
        return purchaseHistoryArrayList;
    }

    public ArrayList<Item> getItemsArrayList() {
        if(itemsArrayList==null){
            itemsArrayList = new ArrayList<>();
            itemsArrayList.add(new Item("Pants",10,20.50));
            itemsArrayList.add(new Item("Shoes",100,10.50));
            itemsArrayList.add(new Item("Hats",30,5.99));
        }
        return itemsArrayList;
    }

    public void addToPurchaseHistoryList(PurchaseHistory historyObject){
        purchaseHistoryArrayList.add(historyObject);
    }
}
