package com.example.servertest.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;

import com.example.servertest.R;

import java.util.ArrayList;

public class DetailOrder extends Activity {
    private ExpandableListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<DetailOrderGroup> DataList = new ArrayList<DetailOrderGroup>();
        listView = (ExpandableListView)findViewById(R.id.lv_order_list);

        DetailOrderGroup temp = new DetailOrderGroup("  전찬혁");
        temp.child.add("후라이드 양념 반반 18,000원");
        DataList.add(temp);

        DetailOrderGroup temp2 = new DetailOrderGroup("  최성진");
        temp2.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp2);

        DetailOrderGroup temp3 = new DetailOrderGroup("  김선현");
        temp3.child.add("후라이드 양념 반반 18,000원");
        temp3.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp3);

        DetailOrderGroup temp4 = new DetailOrderGroup("  박현민");
        temp4.child.add("후라이드 양념 반반 18,000원");
        temp4.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp4);
        DetailOrderAdapter adapter = new DetailOrderAdapter(getApplicationContext(),R.layout.detailorder_parent_list_view,R.layout.detailorder_child_list_view,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
