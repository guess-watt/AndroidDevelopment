package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    TextView tv1,tv2,tv3,selected;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == 1){
            selected.setBackgroundColor(Color.GREEN);
        }
        if(item.getItemId()==2){
            selected.setBackgroundColor(Color.RED);
        }
        if(item.getItemId()==3){
            selected.setBackgroundColor(Color.WHITE);
        }



        return super.onContextItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        selected = (TextView) v;
        menu.add(0,1,0,"Select");
        menu.add(0,2,0,"Remove");
        menu.add(0,3,0,"Clear");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        registerForContextMenu(tv1);
        registerForContextMenu(tv2);
        registerForContextMenu(tv3);


    }
}