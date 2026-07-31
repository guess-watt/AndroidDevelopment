package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    RadioGroup rgGender;
    CheckBox cbJava, cbPython;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        rgGender = findViewById(R.id.rgGender);
        cbJava = findViewById(R.id.cbJava);
        cbPython = findViewById(R.id.cbPython);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {

            String name = etName.getText().toString();

            int id = rgGender.getCheckedRadioButtonId();
            RadioButton rb = findViewById(id);

            String gender = rb.getText().toString();

            String skills = "";

            if (cbJava.isChecked())
                skills += "Java ";

            if (cbPython.isChecked())
                skills += "Python";

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);

            intent.putExtra("name", name);
            intent.putExtra("gender", gender);
            intent.putExtra("skills", skills);

            startActivity(intent);

        });
    }
}