package com.example.cashregisterassignment2ritika;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AlertFragment extends DialogFragment {

    static String selectedQuantity;
    static String selectedItemName;
    static String totalAmount;
    public static AlertFragment newInstance(String quantity, String itemName, String amount){
        selectedQuantity = quantity;
        selectedItemName = itemName;
        totalAmount = amount;
        return new AlertFragment();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(R.string.thank_you_toast_message);
        builder.setMessage("Your purchase is "+selectedQuantity+" "+selectedItemName+" for "+totalAmount);
        return builder.create();
    }
}
