package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class billViewHolder extends RecyclerView.ViewHolder {

    TextView item,price;

    public billViewHolder(@NonNull View itemView) {

        super(itemView);
        item = itemView.findViewById(R.id.itemHolder);
        price = itemView.findViewById(R.id.priceHolder);
    }
}