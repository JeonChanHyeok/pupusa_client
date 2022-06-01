package com.example.detail_order;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class detailOrder extends Activity {
    private ExpandableListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<detailOrderGroup> DataList = new ArrayList<detailOrderGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);

        detailOrderGroup temp = new detailOrderGroup("  전찬혁");
        temp.child.add("후라이드 양념 반반 18,000원");
        DataList.add(temp);

        detailOrderGroup temp2 = new detailOrderGroup("  최성진");
        temp2.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp2);

        detailOrderGroup temp3 = new detailOrderGroup("  김선현");
        temp3.child.add("후라이드 양념 반반 18,000원");
        temp3.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp3);

        detailOrderGroup temp4 = new detailOrderGroup("  박현민");
        temp4.child.add("후라이드 양념 반반 18,000원");
        temp4.child.add("치즈볼 5개              5,000원 ");

        DataList.add(temp4);
        detailOrderAdapter adapter = new detailOrderAdapter(getApplicationContext(),R.layout.detailorder_parent_list_view,R.layout.detailorder_child_list_view,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
