package com.example.restaurantcheckbox;

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
    CheckBox cbBurger,cbPizza,cbJuice;
    Button btnSumbit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cbBurger = findViewById(R.id.cbBurger);
        cbPizza = findViewById(R.id.cbPizza);
        cbJuice = findViewById(R.id.cbJuice);

        btnSumbit = findViewById(R.id.btnSubmit);

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                StringBuilder items = new StringBuilder();
                if(cbBurger.isChecked()){
                    total += 100;
                    items.append("BURGER\n");
                }
                if(cbPizza.isChecked()){
                    total += 150;
                    items.append("PIZZA\n");
                }
                if(cbJuice.isChecked()){
                    total += 80;
                    items.append("JUICE\n");
                }

                String finalstring = items.toString();

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra("ITEMS",finalstring);
                intent.putExtra("AMOUT",total);
                startActivity(intent);

            }
        });



    }
}