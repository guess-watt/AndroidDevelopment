package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.BroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    AirplaneReciever airplaneReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView tvStatus = findViewById(R.id.tvStatus);

        AirplaneReciever airplaneReciever = new AirplaneReciever;
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneReciever,filter);

    }

    class AirplaneReciever extends BroadcastReceiver{
        @Override
        public void onRecieve
    }
}