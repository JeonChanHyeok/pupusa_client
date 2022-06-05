package com.example.store_input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderCheckAdapter extends BaseAdapter {
    ListView listView;
    Button button;

    private ArrayList<OrderCheckItem> orderCheckItemList = new ArrayList<>();

    public OrderCheckAdapter(){}

    // 최초의 화면의 갯수를 설정
    @Override
    public int getCount() {
        return orderCheckItemList.size();
    }

    // 아이템이 클릭될 때 아이템의 데이터를 도출
    @Override
    public Object getItem(int position) {
        return orderCheckItemList.get(position);
    }

    //지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        //"orderHistoryItem" Layout을 infalte하여 convertView 참조 획득.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.order_check_item, parent, false); }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView date = (TextView) convertView.findViewById(R.id.tv_order_check_date);
        TextView address = (TextView) convertView.findViewById(R.id.tv_order_check_address);
        TextView menu = (TextView) convertView.findViewById(R.id.tv_order_check_menu);
        TextView price = (TextView) convertView.findViewById(R.id.tv_order_check_price);

        listView = (ListView) convertView.findViewById(R.id.ll_order_check);
        button = (Button) convertView.findViewById(R.id.btn_order_check_unconfirm);
        //listView.setFocusable(false);
        button.setFocusable(false);

        //Data Seet에서 position에 위치한 데이터 참조 획득
        OrderCheckItem orderItem = orderCheckItemList.get(position);

        price.setText(String.valueOf(orderItem.getPrice()));
        date.setText(orderItem.getDate());
        address.setText(orderItem.getAddress());
        menu.setText(orderItem.getMenu());

        return convertView;
    }

    //item에 데이터 추가
    public void addItem(String address, String menu, String date, int price){
        OrderCheckItem item = new OrderCheckItem();

        item.setPrice(price);
        item.setAddress(address);
        item.setDate(date);
        item.setMenu(menu);

        orderCheckItemList.add(item);
    }
}
