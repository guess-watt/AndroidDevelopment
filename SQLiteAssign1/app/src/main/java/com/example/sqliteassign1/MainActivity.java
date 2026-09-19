package com.example.sqliteassign1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    Button addButton, viewButton;

    com.example.sqliteassign1.DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);

        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.viewButton);

        databaseHelper = new com.example.sqliteassign1.DatabaseHelper(this);

        addButton.setOnClickListener(v -> {

            String studentName = name.getText().toString();
            String studentAge = age.getText().toString();

            if (studentName.isEmpty() || studentAge.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please enter all details",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            int studentAgeValue = Integer.parseInt(studentAge);

            boolean inserted =
                    databaseHelper.insertStudent(
                            studentName,
                            studentAgeValue
                    );

            if (inserted) {

                Toast.makeText(
                        MainActivity.this,
                        "Student added successfully",
                        Toast.LENGTH_SHORT
                ).show();

                name.setText("");
                age.setText("");

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Failed to add student",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        viewButton.setOnClickListener(v -> {

            Intent intent =
                    new Intent(MainActivity.this, ViewStudentsActivity.class);

            startActivity(intent);
        });
    }
}