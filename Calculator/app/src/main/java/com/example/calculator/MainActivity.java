package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   private calculatorBrain cal = new calculatorBrain();
   ArrayList<String> all_buttons; //numbers, operators and null
    //Textview object
    TextView num_text;
    //button object
    Button B_btn1;
    Button B_btn2;
    Button B_btn3;
    Button B_btnadd;
    Button B_btn4;
    Button B_btn5;
    Button B_btn6;
    Button B_btndiv;
    Button B_btn7;
    Button B_btn8;
    Button B_btn9;
    Button B_btnsub;
    Button B_btn0;
    Button B_btnC;
    Button B_btnequals;
    Button B_btnmul;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num_text = findViewById(R.id.result);

        //getting the button and adding in the button list
        B_btn1 = findViewById(R.id.btn1);
        B_btn2 = findViewById(R.id.btn2);
        B_btn3 = findViewById(R.id.btn3);
        B_btnadd = findViewById(R.id.btnadd);
        B_btn4 = findViewById(R.id.btn4);
        B_btn5 = findViewById(R.id.btn5);
        B_btn6 = findViewById(R.id.btn6);
        B_btndiv = findViewById(R.id.btndiv);
        B_btn7 = findViewById(R.id.btn7);
        B_btn8 = findViewById(R.id.btn8);
        B_btn9 = findViewById(R.id.btn9);
        B_btnsub = findViewById(R.id.btnsub);
        B_btn0 = findViewById(R.id.btn0);
        B_btnC = findViewById(R.id.btnC);
        B_btnequals = findViewById(R.id.btnequals);
        B_btnmul = findViewById(R.id.btnmul);

        B_btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.clear();
                num_text.setText("");
            }
        });

        B_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("1");
                num_text.append("1");
            }
        });
        B_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("2");
                num_text.append("2");
            }
        });
        B_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("3");
                num_text.append("3");
            }
        });
        B_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("4");
                num_text.append("4");
            }
        });
        B_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("5");
                num_text.append("5");
            }
        });
        B_btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("6");
                num_text.append("6");
            }
        });
        B_btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("7");
                num_text.append("7");
            }
        });
        B_btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("8");
                num_text.append("8");
            }
        });
        B_btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("9");
                num_text.append("9");
            }
        });
        B_btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("0");
                num_text.append("0");
            }
        });
        B_btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("+");
                num_text.append("+");
            }
        });
        B_btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("/");
                num_text.append("/");
            }
        });
        B_btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("-");
                num_text.append("-");
            }
        });
        B_btnequals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output = cal.calculate();
                 if(output != "failed"){
                    num_text.append(" = " + cal.calculate());
                 }
                 else{
                     num_text.append("= ERROR!");
                 }
            }
        });
        B_btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cal.push("*");
                num_text.append("*");
            }
        });

    }
}