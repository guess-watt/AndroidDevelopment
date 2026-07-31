package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView tvName, tvGender, tvSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        tvName = findViewById(R.id.tvName);
        tvGender = findViewById(R.id.tvGender);
        tvSkills = findViewById(R.id.tvSkills);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String skills = intent.getStringExtra("skills");

        tvName.setText("Name : " + name);
        tvGender.setText("Gender : " + gender);
        tvSkills.setText("Skills : " + skills);
    }
}