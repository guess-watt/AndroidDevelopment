package com.example.a3activitywithoptionsmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView tvOption,tvContext;
    Button btnOption;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == 1){
            finish();
        }
        if(item.getItemId() == 2){
            Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
            intent.putExtra("Setting","setting");
            startActivity(intent);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,0,"Back");
        menu.add(0,2,0,"Setting");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        tvOption = findViewById(R.id.tvOption);
        btnOption = findViewById(R.id.btnOption);
        tvContext = findViewById(R.id.tvContext);

        String data = getIntent().getStringExtra("Options");
        tvOption.setText(data);


        registerForContextMenu(tvContext);



        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}