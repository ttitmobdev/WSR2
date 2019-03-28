package com.example.user.wsr_2.Room;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.wsr_2.API.API;
import com.example.user.wsr_2.API.ApiUtils;
import com.example.user.wsr_2.Device.DeviceAdapter;
import com.example.user.wsr_2.Device.DeviceResponse;
import com.example.user.wsr_2.Device.OneDeviceActivity;
import com.example.user.wsr_2.FollowRooms;
import com.example.user.wsr_2.MainActivity;
import com.example.user.wsr_2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OneRoomActivity extends AppCompatActivity implements DeviceAdapter.OnItemClickListener   {
    TextView roomName;
    ImageView roomPhoto;
    Integer roomId;
    private API api;
    String token;
    List<DeviceResponse> deviceResponses;
    RecyclerView rec;
    DeviceAdapter adapter;
    String name="dfsdf";
    String photo="sdfsdf";
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_room);
        rec = findViewById(R.id.recDev);
        roomName = findViewById(R.id.roomNameId);
        roomPhoto = findViewById(R.id.roomImageId);
        navigationView = findViewById(R.id.nav);



        deviceResponses = new ArrayList<>();
        Intent id = getIntent();
        roomId = id.getIntExtra("ID",-1);
        token = id.getStringExtra("TOKEN");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(layoutManager);
        adapter = new DeviceAdapter(deviceResponses);
        rec.setAdapter(adapter);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.roomsMenu:
                        Intent go = new Intent(OneRoomActivity.this,RoomsActivity.class);
                        go.putExtra("TOKEN",token);
                        startActivity(go);
                        break;
                    case R.id.followRoomsMenu:
                        Intent re = new Intent(OneRoomActivity.this,FollowRooms.class);
                        startActivity(re);
                        break;
                }
                return false;
            }
        });

        api = ApiUtils.getApi();
        loadRoom(token,roomId);
        loadDevice(token,roomId);
    }
    public void loadDevice(String userToken,Integer id) {
        Call<List<DeviceResponse>> call = api.getInfoAboutDevices(userToken, id);
        call.enqueue(new Callback<List<DeviceResponse>>() {
            @Override
            public void onResponse(Call<List<DeviceResponse>> call, Response<List<DeviceResponse>> response) {
                if (response.isSuccessful()) {
                    deviceResponses.addAll(response.body());
                    rec.getAdapter().notifyDataSetChanged();
                    adapter.setOnItemClickListener(OneRoomActivity.this);
                }
            }

            @Override
            public void onFailure(Call<List<DeviceResponse>> call, Throwable t) {

            }
        });
    }
    public void loadRoom(String token, Integer id){
        Call<OneRoomResponse> call = api.getInfoAboutRoom(token, id);
        call.enqueue(new Callback<OneRoomResponse>() {
            @Override
            public void onResponse(Call<OneRoomResponse> call, Response<OneRoomResponse> response) {
                if (response.isSuccessful()){
                    name = response.body().getName();
                    Uri uri = Uri.parse(response.body().getPhoto());

                    roomName.setText(name);
                    Picasso.get().load(uri).into(roomPhoto);

                }
            }

            @Override
            public void onFailure(Call<OneRoomResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent goDev = new Intent(this,OneDeviceActivity.class);
        DeviceResponse clicked = deviceResponses.get(position);
        goDev.putExtra("ID",clicked.getId());
        goDev.putExtra("TOKEN",token);
        startActivity(goDev);
    }
}
