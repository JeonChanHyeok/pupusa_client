package com.example.servertest.payment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;

public class CheckPeoplePayingAdapter extends BaseExpandableListAdapter {
    private Context context;
    private int parentLayout=0;
    private int childLayout=0;
    private ArrayList<CheckPeoplePayingItem> dataList;
    private LayoutInflater layoutInflater = null;

    public CheckPeoplePayingAdapter(Context context, int parentLayout, int childLayout, ArrayList<CheckPeoplePayingItem> dataList) {
        this.context = context;
        this.parentLayout = parentLayout;
        this.childLayout = childLayout;
        this.dataList = dataList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {  //ParentList 원소 개수 반환
        return dataList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {  //ParentList의 position을 long값으로 반환
        return groupPosition;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataList.get(groupPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) { //ChildList의 크기를 int형으로 반환
        return dataList.get(groupPosition).child.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {  // ChildList의 ID로 long 형 값을 반환
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //ParentList의 뷰뷰
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(this.parentLayout, parent, false);
        }
        TextView parentTextView = (TextView) convertView.findViewById(R.id.tv_check_people_paying_parent);
        parentTextView.setText(dataList.get(groupPosition).parentName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(this.childLayout, parent, false);
        }
        TextView childTextView = (TextView) convertView.findViewById(R.id.tv_check_people_paying_child);
        childTextView.setText(dataList.get(groupPosition).child.get(childPosition));
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.cb_check_people_paying_child);
        if(dataList.get(groupPosition).payChk.get(childPosition)){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
