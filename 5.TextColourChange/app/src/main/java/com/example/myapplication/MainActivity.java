package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    boolean isGreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btnChange = findViewById(R.id.btnColor);
        TextView textView = findViewById(R.id.txtColor);

        btnChange.setOnClickListener(view -> {
            if (isGreen) {
                textView.setTextColor(Color.BLUE);
            } else {
                textView.setTextColor(Color.GREEN);
            }
            isGreen = !isGreen;
        });
    }
}