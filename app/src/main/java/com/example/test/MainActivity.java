package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //Button of login
    private Button mBus,mPassenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBus=(Button) findViewById(R.id.bus_login);
        mPassenger=(Button) findViewById(R.id.passanger_login);

        //on click listern of login page for bus
        mBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, BusLoginActivity.class);
                startActivity(intent);
            }
        });

        //on click listern of login page for passenger
        mPassenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, PassengerLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
