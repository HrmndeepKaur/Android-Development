package com.example.cashregister_as2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {
    TextView details;

@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details = findViewById(R.id.detail_textview);
        details.setText(getIntent().getStringExtra("detailedInfo"));
    }
}
