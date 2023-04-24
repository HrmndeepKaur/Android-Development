package com.example.cashregister_as2;

public class Items {
    int available_qty;
    String name;
    double price;

    public Items(){
        available_qty = 0;
        name = null;
        price = 0;
    }

    public Items(int qty, String n, double p){
        available_qty = qty;
        name = n;
        price = p;
    }

    public String toString(){
        return(this.name + "    " + this.available_qty + "\n" + this.price);
    }
}
