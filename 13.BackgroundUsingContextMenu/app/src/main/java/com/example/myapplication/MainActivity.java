package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layout);
        registerForContextMenu(layout);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"Red");
        menu.add(0,2,0,"Green");
        menu.add(0,3,0,"Blue");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1) layout.setBackgroundColor(Color.RED);
        else if(item.getItemId()==2) layout.setBackgroundColor(Color.GREEN);
        else if(item.getItemId()==3) layout.setBackgroundColor(Color.BLUE);
        return true;
    }
}