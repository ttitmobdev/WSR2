package com.example.user.wsr_2.API;

public class ApiUtils {
    private ApiUtils(){}
    public static final String BASEurl = "http://192.168.12.68/";
    public static API getApi(){
        return RetrofitCLient.getClient(BASEurl).create(API.class);
    }
}
