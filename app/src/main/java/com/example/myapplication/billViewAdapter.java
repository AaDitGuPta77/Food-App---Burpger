package com.example.myapplication;

import android.content.Context;
import android.media.RouteListingPreference;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class billViewAdapter extends RecyclerView.Adapter<billViewHolder> {

    Context context;

    public billViewAdapter(Context context, List<priceHolder> items) {
        this.context = context;
        this.items = items;
    }

    List<priceHolder> items;
    @NonNull
    @Override
    public billViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new billViewHolder(LayoutInflater.from(context).inflate(R.layout.bill_layout_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull billViewHolder holder, int position) {
        holder.item.setText(items.get(position).getName());
        holder.price.setText(items.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
