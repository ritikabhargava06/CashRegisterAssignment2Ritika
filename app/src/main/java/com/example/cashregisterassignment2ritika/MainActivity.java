package com.example.cashregisterassignment2ritika;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        if (savedInstanceState!=null){
            if(savedInstanceState.getString("selectedItemName")!=null){
                selectedItemNameTextView.setText(savedInstanceState.getString("selectedItemName"));
                selectedQuantityTextView.setText(savedInstanceState.getString("selectedQuantity"));
                totalAmountTextView.setText(savedInstanceState.getString("totalAmount"));
            }
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
                selectedQuantityTextView.setText("");
                totalAmountTextView.setText("");
                break;
            case R.id.buyButton:
                if (selectedItemNameTextView.getText().toString().isEmpty()||selectedQuantityTextView.getText().toString().isEmpty()){
                    Toast.makeText(this,"All fields are required",Toast.LENGTH_LONG).show();
                }else{
                    int q = Integer.parseInt(selectedQuantityTextView.getText().toString());
                    if (q>((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemQuantity()){
                        Toast.makeText(this,"No enough quantity in Stock!!",Toast.LENGTH_LONG).show();
                    }else{
                        purchase(q);
                        showThankYouAlert();
                    }
                }
                break;
        }
    }

    private void showThankYouAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thank You for your purchase");
        builder.setMessage("Your purchase is "+selectedQuantityTextView.getText()+" "+selectedItemNameTextView.getText()+" for "+totalAmountTextView.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("selectedItemName",selectedItemNameTextView.getText().toString());
        outState.putString("selectedQuantity",selectedQuantityTextView.getText().toString());
        outState.putString("totalAmount",totalAmountTextView.getText().toString());
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
        int newQuantity = ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).getItemQuantity()-purchaseQuantity;
        ((MyApp)getApplication()).getItemsArrayList().get(selectedItemIndex).setItemQuantity(newQuantity);
        baseAdapter.notifyDataSetChanged();
    }
}