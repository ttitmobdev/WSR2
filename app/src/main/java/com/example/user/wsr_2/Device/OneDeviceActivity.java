package com.example.user.wsr_2.Device;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.wsr_2.API.API;
import com.example.user.wsr_2.API.ApiUtils;
import com.example.user.wsr_2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneDeviceActivity extends AppCompatActivity {
    RadioGroup zamokAndKarniz;
    RadioGroup LustraAndSvet;
    SeekBar temp;
    SeekBar termostat;
    Integer deviceId;
    String token;
    API api;
    TextView name;
    TextView type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_device);
        zamokAndKarniz = findViewById(R.id.karnizAndZamokGroup);
        LustraAndSvet = findViewById(R.id.lustraAndSvet);
        temp = findViewById(R.id.tempId);
        termostat = findViewById(R.id.termostatId);
        name = findViewById(R.id.nameDevId);
        type = findViewById(R.id.typeDevId);

        Intent id = getIntent();
        deviceId = id.getIntExtra("ID",-1);
        token = id.getStringExtra("TOKEN");

        api = ApiUtils.getApi();
        loadDevice(deviceId,token);
    }
    public void loadDevice(Integer idDev,String token){
        Call<OneDeviceResp> call = api.getDev(token,idDev);
        call.enqueue(new Callback<OneDeviceResp>() {
            @Override
            public void onResponse(Call<OneDeviceResp> call, Response<OneDeviceResp> response) {
                if (response.isSuccessful()){
                    name.setText(response.body().getName());
                    type.setText(response.body().getType_name());
                    switch(response.body().getType_name()){
                        case "Электронный замок":
                            zamokAndKarniz.setVisibility(View.VISIBLE);
                            LustraAndSvet.setVisibility(View.GONE);
                            temp.setVisibility(View.GONE);
                            termostat.setVisibility(View.GONE);
                            break;
                        case "Электрокарниз":
                            zamokAndKarniz.setVisibility(View.VISIBLE);
                            LustraAndSvet.setVisibility(View.GONE);
                            temp.setVisibility(View.GONE);
                            termostat.setVisibility(View.GONE);
                            break;
                        case "Люстра":
                            zamokAndKarniz.setVisibility(View.GONE);
                            LustraAndSvet.setVisibility(View.VISIBLE);
                            temp.setVisibility(View.GONE);
                            termostat.setVisibility(View.GONE);
                            break;
                        case "Светильник":
                            zamokAndKarniz.setVisibility(View.GONE);
                            LustraAndSvet.setVisibility(View.VISIBLE);
                            temp.setVisibility(View.GONE);
                            termostat.setVisibility(View.GONE);
                            break;
                        case "Датчик температуры":
                            zamokAndKarniz.setVisibility(View.GONE);
                            LustraAndSvet.setVisibility(View.GONE);
                            temp.setVisibility(View.VISIBLE);
                            termostat.setVisibility(View.GONE);
                            break;
                        case "Термостат":
                            zamokAndKarniz.setVisibility(View.GONE);
                            LustraAndSvet.setVisibility(View.GONE);
                            temp.setVisibility(View.GONE);
                            termostat.setVisibility(View.VISIBLE);
                            break;
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"ERroR"+response.raw().code(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<OneDeviceResp> call, Throwable t) {

            }
        });
    }
}
