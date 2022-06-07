package com.example.servertest.payment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.servertest.R;
import com.example.servertest.server.RetrofitClient;
import com.example.servertest.server.ServiceApi;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

public class ConfirmPaymentActivity extends AppCompatActivity {

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();

    ArrayAdapter<String> adapter;
    Button btnConfirmPaymentRequestDialog;
    private CheckBox cbConfirmPaymentDoor;
    private CheckBox cbConfirmPaymentOneday;
    private ExpandableListView listView;
    ListView amountOrderListView, myOrderListView;
    ArrayList<CheckPeoplePayingItem> dataList;
    EditText confirmPaymentRequestEditBox;

    String loginedId;
    Long roomId;

    ConfirmPaymentResponse confirmPaymentResponse;

    private StompClient mStompClient;
    private List<StompHeader> headerList;
    private String wsServerUrl = "ws://10.0.2.2:8080/inchatroom/websocket";
    private static final String TAG = "ConfirmPaymentActivity";

    TextView storeName;
    TextView address;
    TextView allDeliPee;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_payment);

        loginedId = getIntent().getStringExtra("loginedId");
        roomId = getIntent().getLongExtra("roomId", 0L);

        cbConfirmPaymentDoor = findViewById(R.id.cb_confirm_payment_door);
        cbConfirmPaymentOneday = findViewById(R.id.cb_confirm_payment_oneday);
        storeName = (TextView) findViewById(R.id.tv_confirm_payment_store_name);
        address = (TextView) findViewById(R.id.tv_confirm_payment_address);
        allDeliPee = (TextView) findViewById(R.id.tv_confirm_payment_delivery_price);
        AppCompatButton btnPayco = (AppCompatButton) findViewById(R.id.btn_confirm_payment_payco);
        AppCompatButton btntoss = (AppCompatButton) findViewById(R.id.btn_confirm_payment_toss);
        System.out.println("방 번호: " + roomId);
        initStomp();
        initData();

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
        btnPayco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg msg = new Msg(loginedId, roomId);
                String objJson = gson.toJson(msg);
                sendMsg(objJson);
                Toast.makeText(getApplicationContext(), "결제완료", Toast.LENGTH_SHORT).show();
            }
        });
        btntoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Msg msg = new Msg(loginedId, roomId);
                String objJson = gson.toJson(msg);
                sendMsg(objJson);
                Toast.makeText(getApplicationContext(), "결제완료", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void initData(){
        String objJson = gson.toJson(roomId);
        Call call = service.loadPayList(objJson);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                String str = gson.toJson(response.body());
                confirmPaymentResponse = gson.fromJson(str, ConfirmPaymentResponse.class);
                storeName.setText(confirmPaymentResponse.getRoomStoreName());
                address.setText(confirmPaymentResponse.getRoomPickUpAddress());
                allDeliPee.setText(""+confirmPaymentResponse.getRoomDeliPee());
                amountOrder();
                myOrder();
                checkPeoplePaying();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
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
        for(String c: confirmPaymentResponse.getUserName()){
            if(!item.child.contains(c)){
                item.child.add(c);
            }
        }
        int j;
        for(String c: item.child){
            for(j = 0 ; j < confirmPaymentResponse.getUserName().size();j++){
                if(c.equals(confirmPaymentResponse.getUserName().get(j)) && !confirmPaymentResponse.getIsPay().get(j)){
                    item.payChk.add(false);
                    break;
                }
            }
            if(j == confirmPaymentResponse.getUserName().size()) item.payChk.add(true);
        }
        int i=0;
        for(boolean a : item.payChk){
            if(a) i++;
        }
        if(i == item.payChk.size() && loginedId.equals(confirmPaymentResponse.getMasterUserId())){
            findViewById(R.id.btn_confirm_payment_pay).setClickable(true);
        }

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
        adapter.addAll("요청사항을 선택하세요.", "서두르지 않고 안전하게 와주세요",
                "도착 후 전화주시면 직접 받으러 갈게요.",  "직접 입력");
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

    //모든 인원들이 주문한 총 주문 내역
    public void amountOrder(){
        amountOrderListView = findViewById(R.id.ll_confirm_payment_amount_order_history);

        AmountOrderAdapter adapter  = new AmountOrderAdapter();
        amountOrderListView.setAdapter(adapter);
        List<String> menuNames = new ArrayList<>();
        List<Menus> menus = new ArrayList<>();
        for(int i = 0 ; i < confirmPaymentResponse.getMenuName().size() ; i ++){
            if(!menuNames.contains(confirmPaymentResponse.getMenuName().get(i))){
                menuNames.add(confirmPaymentResponse.getMenuName().get(i));
                Menus temp_menu = new Menus(confirmPaymentResponse.getMenuName().get(i), confirmPaymentResponse.getMenuPrice().get(i));
                menus.add(temp_menu);
            }else{
                for(int j=0; j < menus.size();j++){
                    if(confirmPaymentResponse.getMenuName().get(i).equals(menus.get(j).name)){
                        menus.get(j).count++;
                        menus.get(j).price += menus.get(j).price;
                    }
                }
            }
        }
        int allPrice = 0;
        //메뉴
        for(Menus m : menus){
            adapter.addItem(m.name, m.count, m.price);
            allPrice+=m.price;
        }
        allPrice += confirmPaymentResponse.getRoomDeliPee();
        TextView tv = (TextView) findViewById(R.id.tv_confirm_payment_amount_price);

        tv.setText(""+allPrice);
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

    private class Menus{
        String name;
        int count;
        int price;

        public Menus(String name , int price) {
            this.name = name;
            this.count = 1;
            this.price = price;
        }
    }

    //내가 주문한 주문 내역
    public void myOrder(){
        amountOrderListView = findViewById(R.id.ll_confirm_payment_my_order_history);

        AmountOrderAdapter adapter  = new AmountOrderAdapter();
        amountOrderListView.setAdapter(adapter);

        List<String> menuNames = new ArrayList<>();
        List<Menus> menus = new ArrayList<>();
        for(int i = 0 ; i < confirmPaymentResponse.getMenuName().size() ; i ++){
            if(confirmPaymentResponse.getUserId().get(i).equals(loginedId)) {
                if (!menuNames.contains(confirmPaymentResponse.getMenuName().get(i))) {
                    menuNames.add(confirmPaymentResponse.getMenuName().get(i));
                    Menus temp_menu = new Menus(confirmPaymentResponse.getMenuName().get(i), confirmPaymentResponse.getMenuPrice().get(i));
                    menus.add(temp_menu);
                } else {
                    for (int j = 0; j < menus.size(); j++) {
                        if (confirmPaymentResponse.getMenuName().get(i).equals(menus.get(j).name)) {
                            menus.get(j).count++;
                            menus.get(j).price += menus.get(j).price;
                        }
                    }
                }
            }
        }
        int myPirce = 0;
        //메뉴
        for(Menus m : menus){
            adapter.addItem(m.name, m.count, m.price);
            myPirce+=m.price;
        }

        TextView tv2 = (TextView) findViewById(R.id.tv_confirm_payment_my_delivery_price);
        int myDelpee = 0;
        if(loginedId.equals(confirmPaymentResponse.getUnLuckyManId())){
            myDelpee = (int)(confirmPaymentResponse.getRoomDeliPee() / confirmPaymentResponse.getUserCount()) + (confirmPaymentResponse.getRoomDeliPee() % confirmPaymentResponse.getUserCount());
        }else{
            myDelpee = (int)(confirmPaymentResponse.getRoomDeliPee() / confirmPaymentResponse.getUserCount());
        }
        tv2.setText("" + myDelpee);
        TextView tv = (TextView) findViewById(R.id.tv_confirm_payment_my_amount_price);
        tv.setText("" + myPirce + myDelpee);
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


    // 채팅방 입장을 위한 소켓 통신/ 소켓 열기
    @SuppressLint("CheckResult")
    public void initStomp(){
        mStompClient= Stomp.over(Stomp.ConnectionProvider.OKHTTP, wsServerUrl);

        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    if(lifecycleEvent.getException().getMessage().contains("EOF")){

                    }
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });

        mStompClient.topic("/topic/chat/pay/" + roomId)
                .subscribe(topicMessage -> {
                    System.out.println(topicMessage.getPayload());
                    initData();
                });
        // add Header
        headerList=new ArrayList<>();
        mStompClient.connect(headerList);
    }

    public void sendMsg(String c){
        mStompClient.send("/chat/pay" ,c).subscribe();
    }


    private class Msg{
        String userId;
        Long roomId;

        public Msg(String userId, Long roomId) {
            this.userId = userId;
            this.roomId = roomId;
        }
    }
    private class Bill{
        Long roomId;
        String msg;
    }
}
