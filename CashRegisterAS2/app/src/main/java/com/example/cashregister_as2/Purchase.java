package com.example.cashregister_as2;

import java.util.Date;

public class Purchase {
    int quantity;
    String name;
    double price;
    Date date;

    public Purchase(){
        quantity = 0;
        name = null;
        price = 0;
        date = null;
    }
    public Purchase(int q, String n, double p, Date d){
        this.quantity = q;
        this.name = n;
        this.price = p;
        this.date = d;
    }

    public String toString(){
        return(this.name + "       " + this.quantity + "\n" + this.price);
    }

    public static String print(Purchase purchase) {
        return ("Product: " + purchase.name + "\n" + "Price: "+ purchase.price + "\n" + "Purchase Date: " + purchase.date);
    }
}
