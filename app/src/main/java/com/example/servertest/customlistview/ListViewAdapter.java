package com.example.servertest.customlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.servertest.R;

import java.util.ArrayList;
//어댑터를 쉽게하기 위해 BaseAdapter를 상속받는다.
public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>();
    public ListViewAdapter(){
    }
    //오버라이딩해서 list값을 받는다.
    @Override // 몇개인지 확인
    public int getCount() {
        return itemList.size();
    }
    @Override  //  데이터를 보낸다.
    public Object getItem(int position) {
        return itemList.get(position);
    }
    @Override // 위치 값을 받는다.
    public long getItemId(int position) {
        return position;
    }
    @Override //뷰그룹은 뷰를 하나하나 묶은 걸 말한다.
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context c = parent.getContext();
        // 리스트뷰로 된 뷰 그룹들을 고유한 값을 주기 위해 Context를 준다.
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list, parent, false);
        }
        //LayoutInflater 만약에 화면이 보이지 않으면 xml 두개를 합쳐라
        ImageView post = (ImageView)convertView.findViewById(R.id.iv_store_select_list);
        TextView title = (TextView)convertView.findViewById(R.id.tv_store_select_text);
        TextView descript = (TextView)convertView.findViewById(R.id.tv_store_select_text2);
        TextView pickup_charge = (TextView)convertView.findViewById(R.id.tv_store_select_text3);
        TextView pickup_time = (TextView)convertView.findViewById(R.id.tv_store_select_text4);
        //list.xml에 접근하기 위해 변수값
        ListViewItem listitem = itemList.get(pos);
        post.setImageDrawable(listitem.getPost());
        title.setText(listitem.getTitle());
        descript.setText(listitem.getDescript());
        pickup_time.setText(listitem.getPickup_time());
        pickup_charge.setText(listitem.getPickup_charge());

        return convertView;
        //뷰 리턴
    }
    //메인에서 받으면 커스텀으로 보내기 위한
    public void additem(Drawable post, String title, String star_score,String pickup_charge,String pickup_time ){
        ListViewItem item = new ListViewItem();

        item.setPost(post);
        item.setTitle(title);
        item.setDescript(star_score);
        item.setPickup_charge(pickup_charge);
        item.setPickup_time(pickup_time);
        itemList.add(item);
    }

}