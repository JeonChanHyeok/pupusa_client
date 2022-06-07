package com.example.servertest.server;

import com.example.servertest.login.DupResponse;
import com.example.servertest.login.JoinResponse;
import com.example.servertest.login.LoginResponse;
import com.example.servertest.login.User;
import com.example.servertest.order.OrderRoomInfoResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/user/joindupchk")
    Call<DupResponse> userEmailDupChk(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/user/join")
    Call<JoinResponse> userJoin(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/mypage/load")
    Call<User> userInfoLoad(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/roomload")
    Call<Object>  loadRoomList(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/roommake")
    Call<Long> makeChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/loadroominfo")
    Call<Object> loadRoomInfo(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/joinroom")
    Call<Integer> goChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/exitroom")
    Call<Void> exitChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/loadchatmsg")
    Call<Object> loadChatData(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/store/loadstore")
    Call<Void> loadStore(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/order/loadroominfo")
    Call<OrderRoomInfoResponse> loadOrderRoomInfo(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/order/loadorderlist")
    Call<Object> loadOrderList(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/order/makeorder")
    Call<Object> sendOrder(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/getstate")
    Call<Integer> getChatRoomState(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/order/goPay")
    Call<Void> goPay(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/pay/loadPayList")
    Call<Object> loadPayList(@Field("objJson") String objJson);

}
