package com.example.user.wsr_2.Auth;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.wsr_2.API.API;
import com.example.user.wsr_2.API.ApiUtils;
import com.example.user.wsr_2.MainActivity;
import com.example.user.wsr_2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {
    EditText userLogin;
    EditText userPassword;
    Button sign;
    String login;
    String password;
    private API api;
    String userToken;
    private static String rawToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userLogin = findViewById(R.id.loginId);
        userPassword = findViewById(R.id.passwordId);
        sign = findViewById(R.id.signId);
        api = ApiUtils.getApi();
        Log.d("TOKEN", "token");
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(internetConn()==true) {
                    login = userLogin.getText().toString();
                    Log.d("TOKEN", "token");
                    password = userPassword.getText().toString();
                    Log.d("TOKEN", "token");
                    signIn(login, password);
                }
                else
                    Toast.makeText(getApplicationContext(),"Нет соединения с интернетом",Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void signIn(String login,String password){
        Call<TokenResponse> call = api.signIn(login,password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    Log.d("TOKEN", "token");
                    rawToken = response.body().getToken();
                    userToken = rawToken;
                    Log.d("TOKEN", "token" + userToken.toString());
                    Intent go = new Intent(AuthActivity.this,MainActivity.class);
                    go.putExtra("TOKEN",userToken);
                    Log.d("TOKEN", "token" + userToken.toString());
                    startActivity(go);
                }
                else
                    Toast.makeText(getApplicationContext(),"Неверный логин или пароль "+response.raw().code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean internetConn(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        return false;
    }
}
