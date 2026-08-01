package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tvResult = findViewById(R.id.tvResult);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");

        double basic = Double.parseDouble(intent.getStringExtra("salary"));

        double hra = basic * 0.20;
        double da = basic * 0.10;
        double total = basic + hra + da;

        tvResult.setText(
                "Employee Name : " + name +
                        "\nEmployee ID : " + id +
                        "\nBasic Salary : " + basic +
                        "\nHRA (20%) : " + hra +
                        "\nDA (10%) : " + da +
                        "\nTotal Salary : " + total
        );
    }
}