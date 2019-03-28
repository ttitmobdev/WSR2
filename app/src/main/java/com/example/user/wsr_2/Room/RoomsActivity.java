package com.example.user.wsr_2.Room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.wsr_2.API.API;
import com.example.user.wsr_2.API.ApiUtils;
import com.example.user.wsr_2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsActivity extends AppCompatActivity  implements RoomAdapter.OnItemClickListener {

    String userToken;
    private API api;
    List<RoomResponse> roomResponseList;
    RecyclerView recyclerView;
    RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Intent go = getIntent();
        userToken = "Bearer "+go.getStringExtra("TOKEN");
        roomResponseList = new ArrayList<>();
        adapter = new RoomAdapter(roomResponseList);
        recyclerView = findViewById(R.id.rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        api = ApiUtils.getApi();
        loadRoom(userToken);
    }
    public void loadRoom(String token){
        Call<List<RoomResponse>> call = api.getRoom(token);
        call.enqueue(new Callback<List<RoomResponse>>() {
            @Override
            public void onResponse(Call<List<RoomResponse>> call, Response<List<RoomResponse>> response) {
                if (response.isSuccessful()){
                    roomResponseList.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                    adapter.setOnItemClickListener(RoomsActivity.this);
                }
            }

            @Override
            public void onFailure(Call<List<RoomResponse>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onItemClick(int pos){
        Intent goRoom = new Intent(this,OneRoomActivity.class);
        RoomResponse clicked = roomResponseList.get(pos);
        goRoom.putExtra("ID",clicked.getId());
        goRoom.putExtra("TOKEN",userToken);
        startActivity(goRoom);
    }
}
