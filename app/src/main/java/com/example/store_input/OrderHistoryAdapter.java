package com.example.store_input;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.ArrayList;

public class OrderHistoryAdapter extends BaseAdapter {
    public ArrayList<OrderHistoryItem> orderHistoryItemList = new ArrayList<>();

    public OrderHistoryAdapter(){}

    // 최초의 화면의 갯수를 설정
    @Override
    public int getCount() {
        return orderHistoryItemList.size();
    }

    // 아이템이 클릭될 때 아이템의 데이터를 도출
    @Override
    public Object getItem(int position) {
        return orderHistoryItemList.get(position);
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
            convertView = inflater.inflate(R.layout.order_history_item, parent, false); }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView menuName = (TextView) convertView.findViewById(R.id.tv_order_history_menu_name);
        TextView menuNum = (TextView) convertView.findViewById(R.id.tv_order_history_menu_num);
        TextView menuPrice = (TextView) convertView.findViewById(R.id.tv_order_history_menu_price);

        //Data Seet에서 position에 위치한 데이터 참조 획득
        OrderHistoryItem orderItem = orderHistoryItemList.get(position);

        menuName.setText(orderItem.getMenuName());
        menuNum.setText(String.valueOf(orderItem.getMenuNum()));
        menuPrice.setText(String.valueOf(orderItem.getMenuPrice()));

        return convertView;
    }

    //item에 데이터 추가
    public void addItem(String menuName, int menuNum, int menuPrice){
        OrderHistoryItem item = new OrderHistoryItem();

        item.setMenuName(menuName);
        item.setMenuNum(menuNum);
        item.setMenuPrice(menuPrice);


        orderHistoryItemList.add(item);
    }
}
