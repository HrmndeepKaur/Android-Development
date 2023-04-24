package com.example.cashregister_as2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PurchaseBaseAdaptor extends BaseAdapter {
    ArrayList<Purchase> purchase_list;
    Context context;

    public PurchaseBaseAdaptor(ArrayList<Purchase> purchase_list, Context context){
        this.purchase_list = purchase_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return purchase_list.size();
    }

    @Override
    public Object getItem(int i) {
        return purchase_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_list, null);
        TextView purchase_name = view.findViewById(R.id.productRow_1);
        TextView purchase_price = view.findViewById(R.id.productRow_2);

        purchase_name.setText(purchase_list.get(i).name + "\n" + purchase_list.get(i).quantity);
        purchase_price.setText(Double.toString(purchase_list.get(i).price) + "\n");

        return view;
    }
}
