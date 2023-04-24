package com.example.cashregister_as2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Manager extends AppCompatActivity implements View.OnClickListener{
    Button history;
    Button restock;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        history = findViewById(R.id.history_btn);
        restock = findViewById(R.id.restock_btn);

        history.setOnClickListener(this);
        restock.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.history_btn:{
                Intent intent = new Intent(this, History.class);
                startActivity(intent);
                break;
            }
            case R.id.restock_btn:{
                Intent intent = new Intent(this, Restock.class);
                startActivity(intent);
                break;
            }
        }
    }
}
