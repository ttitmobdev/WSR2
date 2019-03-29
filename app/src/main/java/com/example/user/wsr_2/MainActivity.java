package com.example.user.wsr_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.user.wsr_2.Galary.GalaryActivity;
import com.example.user.wsr_2.Room.RoomsActivity;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav);
        Intent token = getIntent();
        final String userT = token.getStringExtra("TOKEN");
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.roomsMenu:
                        Intent go = new Intent(MainActivity.this,RoomsActivity.class);
                        go.putExtra("TOKEN",userT);
                        startActivity(go);
                        break;
                    case R.id.followRoomsMenu:
                        Intent re = new Intent(MainActivity.this,FollowRooms.class);
                        startActivity(re);
                        break;
                    case R.id.mapMenu:
                        Intent map = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(map);
                        break;
                    case R.id.galleryMenu:
                        Intent gal = new Intent(MainActivity.this,GalaryActivity.class);
                        startActivity(gal);
                        break;

                }
                return false;
            }
        });
    }
}
