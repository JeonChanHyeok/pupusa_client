package com.example.servertest.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servertest.R;

import java.util.ArrayList;

public class ConfirmPaymentActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Button btnConfirmPaymentRequestDialog;
    private CheckBox cbConfirmPaymentDoor;
    private CheckBox cbConfirmPaymentOneday;
    private ExpandableListView listView;
    ListView amountOrderListView, myOrderListView;
    ArrayList<CheckPeoplePayingItem> dataList;
    EditText confirmPaymentRequestEditBox;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        cbConfirmPaymentDoor = findViewById(R.id.cb_confirm_payment_door);
        cbConfirmPaymentOneday = findViewById(R.id.cb_confirm_payment_oneday);

        //요청사항 버튼 클릭 시
        findViewById(R.id.btn_confirm_payment_request_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createListDialog();
            }
        });

        //주문사항 체크박스 클릭시
        findViewById(R.id.cb_confirm_payment_door).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCheckboxClicked(v);
            }
        });
        //주문사항 체크박스 클릭시
        findViewById(R.id.cb_confirm_payment_oneday).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onCheckboxClicked(v);
            }
        });


        amountOrder();
        myOrder();
        checkPeoplePaying();
    }

    //EditText이외에 다른 부분 클릭 시 키보드 내려가기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //결제 인원 체크 부분
    public void checkPeoplePaying(){
        dataList = new ArrayList<CheckPeoplePayingItem>();
        listView = (ExpandableListView) findViewById(R.id.el_confirm_payment);

        //여기에 모든 방 인원들 출력
        CheckPeoplePayingItem item = new CheckPeoplePayingItem("결제 인원 확인");
        item.child.add("김선현");
        item.child.add("전찬혁");
        item.child.add("김개똥");
        item.child.add("김순이");
        dataList.add(item);

        CheckPeoplePayingAdapter adapter = new CheckPeoplePayingAdapter(getApplicationContext(),
                R.layout.check_people_paying_parent, R.layout.check_people_paying_child, dataList);

        listView.setAdapter(adapter);
        listView.setIndicatorBounds(10,1900);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long l) {
                setListViewHeight(parent, groupPosition);
                return false;
            }
        });
    }

    //확장형 리스트뷰 동적 높이 계산
    private void setListViewHeight(ExpandableListView listView,
                                   int group) {
        ExpandableListAdapter listAdapter = (ExpandableListAdapter) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            View groupItem = listAdapter.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += groupItem.getMeasuredHeight();
            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    View listItem = listAdapter.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                    totalHeight += listItem.getMeasuredHeight();
                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    //요청사항 클릭시 나타나는 메뉴
    public void createListDialog(){
        confirmPaymentRequestEditBox = findViewById(R.id.et_confirm_payment_request_input);
        btnConfirmPaymentRequestDialog = findViewById(R.id.btn_confirm_payment_request_dialog);

        adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll("요청사항을 선택하세요.", "서두르지 않고 안전하게 와주세요", "벨은 누르지 말아 주세요!",
                "도착 후 전화주시면 직접 받으러 갈게요.", "그냥 문 앞에 놓아 주시면 돼요", "직접 입력");
        adapter.notifyDataSetChanged();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                if(adapter.getItem(position) == "직접 입력"){
                    confirmPaymentRequestEditBox.setVisibility(View.VISIBLE);
                }else{
                    confirmPaymentRequestEditBox.setVisibility(View.GONE);
                }
                String menu = adapter.getItem(position);
                btnConfirmPaymentRequestDialog.setText(menu);
                Toast.makeText(ConfirmPaymentActivity.this, menu, Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }

    //주문 요청사항 체크박스 클릭 이벤트
    public void onCheckboxClicked(View v){
        if(cbConfirmPaymentDoor.isChecked()){
            String str = cbConfirmPaymentDoor.getText().toString();
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
        if(cbConfirmPaymentOneday.isChecked()){
            String str = cbConfirmPaymentOneday.getText().toString();
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    }

    //결제 방법 버튼 클릭 이벤트
    public void paymentMethodButtonClicked(View v){
        if(v.getId() == R.id.btn_confirm_payment_payco){
            Toast.makeText(this, "payco", Toast.LENGTH_SHORT).show();
        } else if(v.getId() == R.id.btn_confirm_payment_toss){
            Toast.makeText(this, "toss", Toast.LENGTH_SHORT).show();
        }
    }

    //모든 인원들이 주문한 총 주문 내역
    public void amountOrder(){
        amountOrderListView = findViewById(R.id.ll_confirm_payment_amount_order_history);

        AmountOrderAdapter adapter  = new AmountOrderAdapter();
        amountOrderListView.setAdapter(adapter);

        //메뉴
        adapter.addItem("쫑뜩한 치킨", 2, 10000);
        adapter.addItem("맛있는 치킨", 3, 50000);
        adapter.addItem("맛있는 치킨", 3, 50000);
        adapter.addItem("맛있는 치킨", 3, 50000);
        adapter.addItem("맛있는 치킨", 3, 50000);

        amountOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                AmountOrderItem item = (AmountOrderItem) parent.getItemAtPosition(position);

                String str = item.getMenu();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
        setListViewHeightBasedOnItems(amountOrderListView);
    }

    //내가 주문한 주문 내역
    public void myOrder(){
        amountOrderListView = findViewById(R.id.ll_confirm_payment_my_order_history);

        AmountOrderAdapter adapter  = new AmountOrderAdapter();
        amountOrderListView.setAdapter(adapter);

        adapter.addItem("쫑뜩한 치킨", 2, 10000);
        adapter.addItem("맛있는 치킨", 1, 2000);

        amountOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                AmountOrderItem item = (AmountOrderItem) parent.getItemAtPosition(position);

                String str = item.getMenu();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });
        setListViewHeightBasedOnItems(amountOrderListView);
    }

    //스크롤 뷰안에 있는 리스트뷰 높이 계산하기 위함
    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            //setDynamicHeight(listView);
            return true;

        } else {
            return false;
        }
    }
}
