package com.example.a3activitywithoptionsmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvHome = findViewById(R.id.tvHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuOption){
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("Options","OPTIONS");
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.menuSetting){
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        }
        return true;
    }
}