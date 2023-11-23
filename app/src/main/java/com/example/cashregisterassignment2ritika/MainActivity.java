package com.example.cashregisterassignment2ritika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
ListView itemsListview;
TextView selectedItemNameTextView;
TextView selectedQuantityTextView;
TextView totalAmountTextView;
Button b1;
Button b2;
Button b3;
Button b4;
Button b5;
Button b6;
Button b7;
Button b8;
Button b9;
Button b0;
Button bClear;
Button bBuy;
Button bManager;
int selectedItemIndex;
boolean isItemSelected = false;
ItemsBaseAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        itemsListview = findViewById(R.id.itemsListView);
        selectedItemNameTextView = findViewById(R.id.selectedProductTextView);
        selectedQuantityTextView = findViewById(R.id.quantityTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b0 = findViewById(R.id.button0);
        bClear = findViewById(R.id.buttonClear);
        bBuy = findViewById(R.id.buyButton);
        bManager = findViewById(R.id.managerButton);

        if (savedInstanceState!=null){
                selectedItemNameTextView.setText(savedInstanceState.getString(getResources().getString(R.string.item_name_saved_instance_key)));
                selectedQuantityTextView.setText(savedInstanceState.getString(getResources().getString(R.string.item_quantity_saved_instance_key)));
                totalAmountTextView.setText(savedInstanceState.getString(getResources().getString(R.string.total_amount_saved_instance_key)));
        }

        baseAdapter = new ItemsBaseAdapter(((MyApp)getApplication()).getItemsArrayList(),this);
        itemsListview.setAdapter(baseAdapter);

        itemsListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isItemSelected=true;
                selectedItemIndex = position;
                selectedItemNameTextView.setText(((MyApp)getApplication()).getItemsArrayList().get(position).getItemName());
                if (!selectedQuantityTextView.getText().toString().isEmpty()) {
                    setTotalAmount();
                }
            }
        });

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bClear.setOnClickListener(this);
        bBuy.setOnClickListener(this);
        bManager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                setQuantityTextView(b1);
                setTotalAmount();
                break;
            case R.id.button2:
                setQuantityTextView(b2);
                setTotalAmount();
                break;
            case R.id.button3:
                setQuantityTextView(b3);
                setTotalAmount();
                break;
            case R.id.button4:
                setQuantityTextView(b4);
                setTotalAmount();
                break;
            case R.id.button5:
                setQuantityTextView(b5);
                setTotalAmount();
                break;
            case R.id.button6:
                setQuantityTextView(b6);
                setTotalAmount();
                break;
            case R.id.button7:
                setQuantityTextView(b7);
                setTotalAmount();
                break;
            case R.id.button8:
                setQuantityTextView(b8);
                setTotalAmount();
                break;
            case R.id.button9:
                setQuantityTextView(b9);
                setTotalAmount();
                break;
            case R.id.button0:
                setQuantityTextView(b0);
                setTotalAmount();
                break;
            case R.id.buttonClear:
                clearAllFields();
                isItemSelected=false;
                break;
            case R.id.buyButton:
                if (selectedItemNameTextView.getText().toString().isEmpty()||selectedQuantityTextView.getText().toString().isEmpty()){
                    Toast.makeText(this,R.string.all_fields_required_toast_message,Toast.LENGTH_LONG).show();
                }else{
                    int q = Integer.parseInt(selectedQuantityTextView.getText().toString());
                    if (q>((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemQuantity()){
                        Toast.makeText(this,R.string.out_of_stock_toast_message,Toast.LENGTH_LONG).show();
                        selectedQuantityTextView.setText("");
                        totalAmountTextView.setText("");
                    }else{
                        purchase(q);
                        showThankYouAlert();
                        clearAllFields();
                        isItemSelected=false;
                    }
                }
                break;
            case R.id.managerButton:
                Intent managerIntent = new Intent(MainActivity.this,ManagerActivity.class);
                startActivity(managerIntent);
                break;
        }
    }

    private void clearAllFields() {
        selectedItemNameTextView.setText("");
        selectedQuantityTextView.setText("");
        totalAmountTextView.setText("");
    }

    private void showThankYouAlert() {
        AlertFragment.newInstance(selectedQuantityTextView.getText().toString(),selectedItemNameTextView.getText().toString(),totalAmountTextView.getText().toString()).show(getSupportFragmentManager(),null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.thank_you_toast_message);
//        builder.setMessage("Your purchase is "+selectedQuantityTextView.getText()+" "+selectedItemNameTextView.getText()+" for "+totalAmountTextView.getText());
//        AlertDialog alertDialog = builder.create();
        //alertDialog.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getResources().getString(R.string.item_name_saved_instance_key),selectedItemNameTextView.getText().toString());
        outState.putString(getResources().getString(R.string.item_quantity_saved_instance_key),selectedQuantityTextView.getText().toString());
        outState.putString(getResources().getString(R.string.total_amount_saved_instance_key),totalAmountTextView.getText().toString());
    }

    public void setQuantityTextView(Button pressedButton){
        String resultText = (String) selectedQuantityTextView.getText();
        String newResult = resultText+pressedButton.getText();
        selectedQuantityTextView.setText(newResult);
    }

    public void setTotalAmount(){
        if (isItemSelected){
            double itemPrice = ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemPrice();
            double total = Integer.parseInt(selectedQuantityTextView.getText().toString()) * itemPrice;
            totalAmountTextView.setText(String.format("%.2f",total));
        }
    }
    private void purchase(int purchaseQuantity) {
        String itemName = ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemName();
        double totalPrice = Double.parseDouble(totalAmountTextView.getText().toString());
        int newQuantity = ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemQuantity()-purchaseQuantity;
        ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).setItemQuantity(newQuantity);
        baseAdapter.notifyDataSetChanged();
        createHistoryObject(itemName, purchaseQuantity, totalPrice);
    }

    private void createHistoryObject(String itemName, int purchasedQuantity, double totalPrice) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getResources().getString(R.string.date_format_string));
        LocalDateTime now = LocalDateTime.now();
        PurchaseHistory history = new PurchaseHistory(itemName, purchasedQuantity,totalPrice,dtf.format(now));
        ((MyApp)getApplication()).addToPurchaseHistoryList(history);
    }
}