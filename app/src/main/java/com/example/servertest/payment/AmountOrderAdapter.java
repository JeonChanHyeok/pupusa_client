package com.example.servertest.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class AmountOrderAdapter extends BaseAdapter {
    private ArrayList<AmountOrderItem> AmountOrderItemList = new ArrayList<AmountOrderItem>();

    public AmountOrderAdapter(){}

    @Override
    public int getCount() {
        return AmountOrderItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return AmountOrderItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.amount_order_item,parent,false);
        }

        TextView menuTextView = (TextView) convertView.findViewById(R.id.tv_amount_order_history_menu);
        TextView menu_numTextView = (TextView) convertView.findViewById(R.id.tv_amount_order_history_menu_num);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.tv_amount_order_history_price);

        AmountOrderItem amountOrderItem = AmountOrderItemList.get(position);

        menuTextView.setText(amountOrderItem.getMenu());
        menu_numTextView.setText(String.valueOf(amountOrderItem.getMenu_num()));
        priceTextView.setText(String.valueOf(amountOrderItem.getPrice()));

        return convertView;
    }

    public void addItem(String menu, int num, int price){
        AmountOrderItem item = new AmountOrderItem();

        item.setMenu_num(num);
        item.setPrice(price);
        item.setMenu(menu);

        AmountOrderItemList.add(item);
    }
}
