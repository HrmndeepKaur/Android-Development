package com.example.cashregister_as2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    NumberPicker num_picker;
    String[] pick_vals;
    int pickd_vals;
    TextView prodName;
    Button Manager;
    Button Buy;
    TextView total;
    TextView quantity;
    ListView prodList;
    ArrayList<Items> items;

    Items selected_item;
    double total_price;
    AlertDialog.Builder builder;
    ItemBaseAdaptor adptr;

    private static final DecimalFormat dcm = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_picker = findViewById(R.id.num_picker);
        prodName = findViewById(R.id.prods_text_view);
        Manager = findViewById(R.id.manager_btn);
        total = findViewById(R.id.total_price);
        quantity = findViewById(R.id.qty);
        Buy = findViewById(R.id.buy_btn);
        prodList = findViewById(R.id.prod_list);
        builder = new AlertDialog.Builder(this);

        Buy.setOnClickListener(this);
        Manager.setOnClickListener(this);

        items = ((MyAPP)getApplication()).item_list;

        adptr = new ItemBaseAdaptor(items, this);
        prodList.setAdapter(adptr);

        prodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_item = items.get(i);
                prodName.setText(selected_item.name);

                if(pickd_vals != 0){
                    total_price = Double.parseDouble(dcm.format(pickd_vals * selected_item.price));

                    total.setText(Double.toString(total_price));
                    if(pickd_vals > selected_item.available_qty)
                        Toast.makeText(MainActivity.this, "Not Enough Quantity in the Stock!!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        pick_vals = new String[101];
        for(int i = 0; i < 101; i++){
            pick_vals[i] = Integer.toString(i);
        }
        num_picker.setMinValue(0);
        num_picker.setMaxValue(100);
        num_picker.setDisplayedValues(pick_vals);

        num_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                pickd_vals = numberPicker.getValue();

                if(pickd_vals != 0)
                    quantity.setText(Integer.toString(pickd_vals));
                if(selected_item != null && pickd_vals != 0 ){
                    total_price = Double.parseDouble(dcm.format(pickd_vals * selected_item.price));

                    total.setText(Double.toString(total_price));
                    if(pickd_vals > selected_item.available_qty)
                        Toast.makeText(MainActivity.this, "Not Enough Quantity in the Stock!!!!", Toast.LENGTH_LONG).show();
                }
                if(pickd_vals == 0){
                    quantity.setText(R.string.quantity);
                    total.setText(R.string.total);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id)
        {
            case R.id.manager_btn: {
                Intent intent = new Intent(this, Manager.class);
                startActivity(intent);
                break;
            }

            case R.id.buy_btn: {
                if(pickd_vals == 0 || selected_item == null)
                    Toast.makeText(this, "All fields are required!!!", Toast.LENGTH_LONG).show();
                else if(pickd_vals > selected_item.available_qty)
                {
                    Toast.makeText(this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Purchase purchased = new Purchase(pickd_vals, selected_item.name, total_price, new Date());
                    selected_item.available_qty -= pickd_vals;
                    selected_item = null;
                    pickd_vals = 0;
                    prodName.setText(R.string.product_name);
                    quantity.setText(R.string.quantity);
                    total.setText(R.string.total);

                    ((MyAPP)getApplication()).purchase.add(purchased);

                    adptr = new ItemBaseAdaptor(items, this);
                    prodList.setAdapter(adptr);

                    builder.setTitle("Thank You for your purchase");
                    builder.setMessage("your purchase is " + purchased.quantity + " " + purchased.name + " for " +  purchased.price);
                    builder.setCancelable(true);
                    builder.show();
                }
                break;
            }

        }
    }
    }
