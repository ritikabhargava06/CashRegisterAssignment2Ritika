package com.example.cashregisterassignment2ritika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RestockActivity extends AppCompatActivity {

    EditText quantityEditText;
    Button bOk;
    Button bCancel;
    ListView restockListView;
    int selectedIndex=-1;
    ItemsBaseAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        quantityEditText = findViewById(R.id.restockQuantityEditText);
        bOk = findViewById(R.id.okButton);
        bCancel = findViewById(R.id.cancelButton);
        restockListView = findViewById(R.id.restockItemsListView);
        baseAdapter = new ItemsBaseAdapter(((MyApp)getApplication()).getItemsArrayList(),this);
        restockListView.setAdapter(baseAdapter);

        restockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }
        });

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityEditText.getText().toString().isEmpty()||selectedIndex==-1){
                    Toast.makeText(RestockActivity.this,R.string.all_fields_required_toast_message,Toast.LENGTH_LONG).show();
                }else{
                    int restockQuantity = Integer.parseInt(quantityEditText.getText().toString());
                    updateItemQuantity(restockQuantity);
                    baseAdapter.notifyDataSetChanged();
                    quantityEditText.setText("");
                    selectedIndex = -1;
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateItemQuantity(int restockQuantity) {
        int newQuantity = ((MyApp)getApplication()).getItemsArrayList().get(selectedIndex).getItemQuantity()+restockQuantity;
        ((MyApp)getApplication()).getItemsArrayList().get(selectedIndex).setItemQuantity(newQuantity);
    }
}