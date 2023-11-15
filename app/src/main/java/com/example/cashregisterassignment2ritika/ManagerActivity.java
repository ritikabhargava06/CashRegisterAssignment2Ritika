package com.example.cashregisterassignment2ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button bHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        bHistory = findViewById(R.id.historyButton);

        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyListIntent = new Intent(ManagerActivity.this,HistoryListActivity.class);
                startActivity(historyListIntent);
            }
        });
    }
}