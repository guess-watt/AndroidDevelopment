package com.example.batterypercentageusingbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {
    TextView tvpercentage,tvstatus;
    BatteryReciever reciever;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvpercentage = findViewById(R.id.tvpercent);
        tvstatus = findViewById(R.id.tvstatus);
        reciever = new BatteryReciever();

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(reciever, filter);

    }


    public class BatteryReciever extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",-1);
            int status = intent.getIntExtra("status", BatteryManager.BATTERY_STATUS_UNKNOWN);
            tvpercentage.setText("PERCENTAGE :::"+level);
            if(status == BatteryManager.BATTERY_STATUS_CHARGING){
                tvstatus.setText("CHARGING");
            }
            else if(status == BatteryManager.BATTERY_STATUS_FULL){
                tvstatus.setText("BATTERY STATUS :::"+"FULL CHARGE");
            }
            else{
                tvstatus.setText("BATTERY STATUS :::"+"NOT CHARGING");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.oprefresh) {
            Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.opexit){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

