package com.example.project_ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ExpandableListView;
import java.util.ArrayList;

public class Announcement extends Activity {
    private ExpandableListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        ArrayList<AnnouncementGroup> DataList = new ArrayList<AnnouncementGroup>();
        listView = (ExpandableListView)findViewById(R.id.mylist);
        AnnouncementGroup temp = new AnnouncementGroup("공지사항");
        temp.child.add("안녕하세요 행복한 배달입니다. \n개인정보처리방침 일부가 아래와 같이 계정될 예정임을 알려드립니다.\n \n ■ 주요 개정내용\n <개인정보 수집 이용>\n\n ■ 개정일자\n-공고일자 : 22년 5월 30일\n-시행일자 : 22년 6월 7일");

        DataList.add(temp);
        AnnouncementAdapter adapter = new AnnouncementAdapter(getApplicationContext(),R.layout.announcement_parent_list_view,R.layout.announcement_child_list_view,DataList);
        listView.setIndicatorBounds(width-50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}
