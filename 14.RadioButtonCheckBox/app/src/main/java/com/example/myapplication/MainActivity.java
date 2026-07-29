package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    CheckBox cbReading, cbMusic;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg = findViewById(R.id.radioGroup);
        cbReading = findViewById(R.id.cbReading);
        cbMusic = findViewById(R.id.cbMusic);
        btnShow = findViewById(R.id.btnShow);

        btnShow.setOnClickListener(v -> {

            int id = rg.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);

            String result = "Gender: " + rb.getText() + "\nHobbies: ";

            if (cbReading.isChecked())
                result += "Reading ";

            if (cbMusic.isChecked())
                result += "Music";

            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        });
    }
}