package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class foodAdapter extends RecyclerView.Adapter<foodAdapter.foodViewHolder> {

    ArrayList<foodModel> list;
    Context context;
    private SelectListner listner;


    public foodAdapter(ArrayList<foodModel> list, Context context, SelectListner listner) {

        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public foodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.model, parent, false);
        return new foodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull foodViewHolder holder, int position) {

        foodModel mOdel = list.get(position);
        holder.img.setImageResource(mOdel.getPic());
        holder.txt.setText(mOdel.getText());
        holder.btn.setText(mOdel.getBtnId());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemClicked(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class foodViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        Button btn;

        public CardView cardView;

        public foodViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv);
            txt = itemView.findViewById(R.id.tv);
            btn = itemView.findViewById(R.id.btn);
            cardView = itemView.findViewById(R.id.main_card);
        }
    }
}


