package com.example.studentdetailssecondactivityoptionsmenu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView tvname,tvRoll,tvage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        tvname = findViewById(R.id.tvName);
        tvRoll = findViewById(R.id.tvRoll);
        tvage = findViewById(R.id.tvAge);

        String name = getIntent().getStringExtra("NAME");
        String roll = getIntent().getStringExtra("ROLL NO.");
        String age = getIntent().getStringExtra("AGE");

        tvname.setText("Name:"+name);
        tvRoll.setText("Course"+roll);
        tvage.setText("Age"+age);


    }
}