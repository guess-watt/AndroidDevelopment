package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.btn1);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert Dialog");
                builder.setMessage("Do you wannt to Continue");
                builder.setPositiveButton("Yes",(dialog, which) ->{
                    Toast.makeText(MainActivity.this,"You Clicked Yes",Toast.LENGTH_LONG).show();
                });
                builder.setNegativeButton("No",(dialog, which) ->{
                    Toast.makeText(MainActivity.this,"You Clicked No",Toast.LENGTH_LONG).show();
                });

                builder.show();
            }
        });

    }
}