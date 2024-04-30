package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<priceHolder> item;
    TextView total;
    Button placeOrder, btoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.BillView);
        placeOrder = findViewById(R.id.placeOrder);
        btoHome = findViewById(R.id.BackToHome);
        total = findViewById(R.id.Total);


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Bundle");
        assert args != null;
        ArrayList<priceHolder> object = (ArrayList<priceHolder>) args.getSerializable("order");

        Iterator<priceHolder> iterator = object.iterator();


        item = new ArrayList<>();

        double totalPrice = 0.0; // Initialize total price to 0
        while (iterator.hasNext()) {
            priceHolder currentItem = iterator.next();
            item.add(currentItem);
            double itemPrice = Double.parseDouble(currentItem.getPrice()); // Assuming price is stored as a String
            totalPrice += itemPrice;
            Log.d("item", currentItem.getName() + " - Price: " + itemPrice);
        }
        Log.d("Total Price", "Total Price of all items: " + totalPrice);

        total.setText("Total is " + totalPrice);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new billViewAdapter(getApplicationContext(), item));

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, ThankYou.class);
                startActivity(intent);
                finish();
            }
        });

        btoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}