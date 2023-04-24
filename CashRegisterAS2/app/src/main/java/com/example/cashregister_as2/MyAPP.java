package com.example.cashregister_as2;

import android.app.Application;

import java.util.ArrayList;

public class MyAPP extends Application {
    ArrayList<Items> item_list = new ArrayList<Items>() {
        {
        add(new Items(10, "Pants",20.44));
        add(new Items(100, "Shoes",10.44));
        add(new Items(30, "Hats",5.9));
        }
 };
ArrayList<Purchase> purchase = new ArrayList<Purchase>(0);
}
