package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        CheckBox pizza = findViewById(R.id.cbPizza);
        CheckBox burger = findViewById(R.id.cbBurger);
        CheckBox sandwich = findViewById(R.id.cbSandwich);

        Button submit = findViewById(R.id.btnSumbit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summary = "";
                int total = 0;

                if(pizza.isChecked()){
                    total += 200;
                    summary += "Pizza\n";
                }
                if(burger.isChecked()){
                    total += 100;
                    summary += "Burger\n";
                }
                if(sandwich.isChecked()){
                    total += 150;
                    summary+="Sandwich\n";
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("summary",summary);
                intent.putExtra("total",total);
                startActivity(intent);
            }
        });
    }
}