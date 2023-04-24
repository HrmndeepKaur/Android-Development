package com.example.cashregister_as2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Restock extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Items> item_list;
    Button okBtn;
    Button cancelBtn;

    EditText quantity;
    ListView stock;

    ItemBaseAdaptor adptor;
    Items selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        item_list = ((MyAPP)getApplication()).item_list;
        okBtn = findViewById(R.id.ok_btn);
        cancelBtn = findViewById(R.id.cancel_btn);
        quantity= findViewById(R.id.new_qty);
        stock = findViewById(R.id.stock);
        adptor = new ItemBaseAdaptor(item_list, this);

        okBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);


        stock.setAdapter(adptor);

        stock.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = item_list.get(i);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id){
            case R.id.ok_btn: {
                if(selected != null && !quantity.getText().toString().isEmpty())
                {
                    selected.available_qty += Integer.parseInt(quantity.getText().toString());
                    adptor = new ItemBaseAdaptor(item_list, this);
                    stock.setAdapter(adptor);
                }
                else
                {
                    Toast.makeText(Restock.this, "Please select product and enter quantity!!!", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.cancel_btn: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
        }
    }

