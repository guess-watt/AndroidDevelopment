package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        TextView summary = findViewById(R.id.tvSummary);
        TextView amount = findViewById(R.id.tvtotal);

        String items = getIntent().getStringExtra("summary");
        int total = getIntent().getIntExtra("total",0);

        summary.setText("Items are : \n"+items);
        amount.setText("Total Amount is "+total);


    }
}