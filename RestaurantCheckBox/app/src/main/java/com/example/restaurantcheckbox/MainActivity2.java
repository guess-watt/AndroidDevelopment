package com.example.restaurantcheckbox;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView tvSummary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        tvSummary = findViewById(R.id.tvSummary);

        String item = getIntent().getStringExtra("ITEMS");
        int total = getIntent().getIntExtra("AMOUT",0);

        String result = item + "\n" + "total = "+total;

        tvSummary.setText(result);



    }
}