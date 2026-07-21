package com.example.sumandavgof3marks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText physics;
    EditText mathematics;
    EditText chemistry;
    Button calculate;

    TextView sum;
    TextView avg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        physics = findViewById(R.id.etPhysics);
        mathematics = findViewById(R.id.etMaths);
        chemistry = findViewById(R.id.etChemistry);

        calculate = findViewById(R.id.btnCalculate);
        sum = findViewById(R.id.tvSum);
        avg = findViewById(R.id.tvAvg);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mark1 = Integer.parseInt(physics.getText().toString());
                int mark2 = Integer.parseInt(mathematics.getText().toString());
                int mark3 = Integer.parseInt(chemistry.getText().toString());
                int total = mark1+mark2+mark3;
                float average = total/3.0f;
                sum.setText("Total marks is "+total);
                avg.setText("Avgerage mark is "+average);


            }
        });

    }
}