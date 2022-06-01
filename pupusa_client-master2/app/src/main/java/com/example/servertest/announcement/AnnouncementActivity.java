package com.example.servertest.announcement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;

import com.example.servertest.R;

import java.util.ArrayList;

public class AnnouncementActivity extends Activity {
    private ExpandableListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<AnnouncementGroup> DataList = new ArrayList<AnnouncementGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);
        AnnouncementGroup temp = new AnnouncementGroup("공지사항  22.05.24");
        temp.child.add("안녕하세요 행복한 배달입니다. \n더 나은 서비스 제공을 위해 시스템 점검이 진행 될 예정입니다.\n \n ■ 점검 일시\n -22년 05월 24일(수) 오전 18시~21시(약 3시간)\n\n 점검시간은 상황에 따라 조기 종료되거나 연장될 수 있습니다.\n 앞으로도 더 나은 서비스 제공을 위해 최선을 다하겠습니다. \n 감사합니다.");

        DataList.add(temp);

        AnnouncementGroup temp2 = new AnnouncementGroup("공지사항  22.05.16");
        temp2.child.add("안녕하세요 행복한 배달입니다. \n더 나은 서비스 제공을 위해 시스템 점검이 진행 될 예정입니다.\n \n ■ 점검 일시\n -22년 05월 16일(화) 오전 05시~07시(약 2시간)\n\n 점검시간은 상황에 따라 조기 종료되거나 연장될 수 있습니다.\n 앞으로도 더 나은 서비스 제공을 위해 최선을 다하겠습니다. \n 감사합니다.");

        DataList.add(temp2);
        AnnouncementAdapter adapter = new AnnouncementAdapter(getApplicationContext(),R.layout.announcement_parent_list_view,R.layout.announcement_child_list_view,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
