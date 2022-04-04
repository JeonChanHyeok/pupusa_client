package com.example.servertest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceApi {
    @FormUrlEncoded
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/user/join")
    Call<JoinResponse> userJoin(@Field("objJson") String objJson);

}
