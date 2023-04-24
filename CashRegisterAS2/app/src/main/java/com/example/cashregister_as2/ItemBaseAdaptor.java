package com.example.cashregister_as2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemBaseAdaptor extends BaseAdapter {

    ArrayList<Items> items;
    Context context;

    public ItemBaseAdaptor(ArrayList<Items> items, Context context){
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.items_list, null);
        TextView item_name = view.findViewById(R.id.productRow_1);
        TextView item_qty = view.findViewById(R.id.productRow_2);

        item_name.setText(items.get(i).name + "\n" + items.get(i).price);
        item_qty.setText(Integer.toString(items.get(i).available_qty) + "\n");
        return view;
    }
}
