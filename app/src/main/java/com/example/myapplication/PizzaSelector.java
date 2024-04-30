package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class PizzaSelector extends AppCompatActivity implements SelectListner{

    RecyclerView recyclerview;
    ArrayList<foodModel> list;
    ImageView cart;

    ImageView BackToHome1;
    ArrayList<priceHolder> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_selector);

        recyclerview = findViewById(R.id.recyclerView);

        cart = findViewById(R.id.Cart);
        BackToHome1 = findViewById(R.id.BackToHome1);


        list = new ArrayList<>();

        list.add(new foodModel(R.drawable.p500, getString(R.string.regular_pizza), "Add to Cart",550));
        list.add(new foodModel(R.drawable.p501, getString(R.string.cheese_pizza), "Add to Cart",530));
        list.add(new foodModel(R.drawable.p502, getString(R.string.tandoori_pizza), "Add to Cart",650));
        list.add(new foodModel(R.drawable.p503, getString(R.string.peri_peri_pizza), "Add to Cart",899));
        list.add(new foodModel(R.drawable.p504, getString(R.string.paneer_cheese_pizza), "Add to Cart",1000));
        list.add(new foodModel(R.drawable.p505, getString(R.string.garden_fresh_pizza), "Add to Cart",996));
        foodAdapter adapter = new foodAdapter(list, this, (SelectListner) this);
        recyclerview.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // PASS Array list to cart activity
                Intent intent = new Intent(PizzaSelector.this,Cart.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order",(Serializable) arrayList);
                intent.putExtra("Bundle",bundle);
                startActivity(intent);
            }
        });

        BackToHome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PizzaSelector.this, HomePage.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(foodModel foodmodel) {
        Toast.makeText(this,  foodmodel.getText() + " Added to cart", Toast.LENGTH_SHORT).show();

        priceHolder v = new priceHolder();
        v.name = foodmodel.getText();
        v.price = String.valueOf(foodmodel.getPrice());
        arrayList.add(v);
    }


}