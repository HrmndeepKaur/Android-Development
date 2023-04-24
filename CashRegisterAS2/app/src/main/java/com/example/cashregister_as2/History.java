package com.example.cashregister_as2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ListView list;
    ArrayList purchase_prods;
    PurchaseBaseAdaptor adptr;
    ArrayList<Purchase> purchased_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        list = findViewById(R.id.purchased_prods);
        purchase_prods = ((MyAPP)getApplication()).purchase;

        adptr = new PurchaseBaseAdaptor(purchase_prods, this);
        list.setAdapter(adptr);


        Intent intent = new Intent(this, Details.class);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("detailedInfo", Purchase.print((Purchase)purchase_prods.get(i)));
                startActivity(intent);
            }
        });
    }
}
