package com.example.cashregisterassignment2ritika;

public class PurchaseHistory {

    String purchasedItemName;
    int purchasedItemQuantity;
    double purchasedTotalPrice;
    String date;

    public PurchaseHistory(String purchasedItemName, int purchasedItemQuantity, double purchasedTotalPrice, String date) {
        this.purchasedItemName = purchasedItemName;
        this.purchasedItemQuantity = purchasedItemQuantity;
        this.purchasedTotalPrice = purchasedTotalPrice;
        this.date = date;
    }
    public String getPurchasedItemName() {
        return purchasedItemName;
    }

    public int getPurchasedItemQuantity() {
        return purchasedItemQuantity;
    }

    public double getPurchasedTotalPrice() {
        return purchasedTotalPrice;
    }

    public String getDate() {
        return date;
    }


}
