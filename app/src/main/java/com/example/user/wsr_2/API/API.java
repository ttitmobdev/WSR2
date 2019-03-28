package com.example.user.wsr_2.API;

import com.example.user.wsr_2.Auth.TokenResponse;
import com.example.user.wsr_2.Device.DeviceResponse;
import com.example.user.wsr_2.Device.OneDeviceResp;
import com.example.user.wsr_2.Room.OneRoomResponse;
import com.example.user.wsr_2.Room.RoomResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {
    @POST("/smart-home/api/login")
    @FormUrlEncoded
    Call<TokenResponse> signIn(@Field("login") String login, @Field("password") String password);

    @GET("/smart-home/api/rooms")
    Call<List<RoomResponse>> getRoom(@Header("Authorization")String token);

    @GET("/smart-home/api/rooms/{id}")
    Call<OneRoomResponse> getInfoAboutRoom (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("/smart-home/api/rooms/{id}/devices")
    Call<List<DeviceResponse>> getInfoAboutDevices (@Header("Authorization") String token, @Path("id") Integer id);

    @GET("/smart-home/api/rooms/devices/{id}")
    Call<OneDeviceResp> getDev (@Header("Authorization") String token, @Path("id") Integer id);

}
