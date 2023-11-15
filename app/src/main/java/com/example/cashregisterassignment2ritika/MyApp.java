package com.example.cashregisterassignment2ritika;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    ArrayList<Item> itemsArrayList;

    public ArrayList<Item> getItemsArrayList() {
        if(itemsArrayList==null){
            itemsArrayList = new ArrayList<>();
            itemsArrayList.add(new Item("Pants",10,20.50));
            itemsArrayList.add(new Item("Shoes",100,10.50));
            itemsArrayList.add(new Item("Hats",30,5.99));
        }
        return itemsArrayList;
    }
}
