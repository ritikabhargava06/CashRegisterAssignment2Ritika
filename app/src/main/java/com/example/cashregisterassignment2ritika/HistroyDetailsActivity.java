package com.example.cashregisterassignment2ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistroyDetailsActivity extends AppCompatActivity {

    TextView detailsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy_details);

        detailsTextView = findViewById(R.id.historyDetailTextView);
        int index = getIntent().getExtras().getInt("selectedPosition");
        PurchaseHistory selectedHistoryObject = ((MyApp)getApplication()).getPurchaseHistoryArrayList().get(index);
        String detailString = "Product: "+ selectedHistoryObject.getPurchasedItemName()+"\nPrice: "+ selectedHistoryObject.getPurchasedTotalPrice()+"\nPurchase Date: "+ selectedHistoryObject.getDate();
        detailsTextView.setText(detailString);

    }
}