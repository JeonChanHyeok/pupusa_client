package com.example.servertest.server;

import com.example.servertest.login.DupResponse;
import com.example.servertest.login.JoinResponse;
import com.example.servertest.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/user/joinemaildupchk")
    Call<DupResponse> userEmailDupChk(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/user/join")
    Call<JoinResponse> userJoin(@Field("objJson") String objJson);

    @POST("/chat/roomload")
    Call<Object>  loadRoomList();

    @FormUrlEncoded
    @POST("/chat/roommake")
    Call<Object> makeChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/joinroom")
    Call<Void> goChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/exitroom")
    Call<Void> exitChatRoom(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/loadchatmsg")
    Call<Object> loadChatData(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/chat/sendchatmsg")
    Call<Void> sendChatData(@Field("objJson") String objJson);


}
