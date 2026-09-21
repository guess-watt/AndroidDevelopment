package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        String course = intent.getStringExtra("course");

        TextView details = findViewById(R.id.details);
        details.setText(
                "Name: "+name+
                "\nAge: "+age+
                "\nCourse: "+course
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.about) {
            Toast.makeText(this, "Student Details App", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.exit) {
            finish();
        }

        return true;
    }
}