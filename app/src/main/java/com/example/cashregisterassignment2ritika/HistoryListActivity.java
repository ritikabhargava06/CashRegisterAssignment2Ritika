package com.example.cashregisterassignment2ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class HistoryListActivity extends AppCompatActivity {

    ListView historyListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        historyListView = findViewById(R.id.historyListView);
       ItemsBaseAdapter historyBaseAdapter = new ItemsBaseAdapter(((MyApp)getApplication()).getPurchaseHistoryArrayList(),this,false);
        historyListView.setAdapter(historyBaseAdapter);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(HistoryListActivity.this,HistroyDetailsActivity.class);
                detailsIntent.putExtra(getResources().getString(R.string.selected_position_extras_key),position);
                startActivity(detailsIntent);
            }
        });
    }
}