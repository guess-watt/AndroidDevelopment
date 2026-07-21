package com.example.myapplication;

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
    EditText num1,num2;
    Button add,sub,mul,div;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.etnum1);
        num2 = findViewById(R.id.etnum2);

        add = findViewById(R.id.btnsum);
        sub = findViewById(R.id.btndiff);
        mul = findViewById(R.id.btnprod);
        div = findViewById(R.id.btndiv);

        result = findViewById(R.id.tvresult);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a = Double.parseDouble(num1.getText().toString());
                double b = Double.parseDouble(num2.getText().toString());
                result.setText("Sum is "+(a+b));
            }
        });
        sub.setOnClickListener(v -> {
            double a = Double.parseDouble(num1.getText().toString());
            double b = Double.parseDouble(num2.getText().toString());
            result.setText("Difference = " + (a - b));
        });
        mul.setOnClickListener(v -> {
            double a = Double.parseDouble(num1.getText().toString());
            double b = Double.parseDouble(num2.getText().toString());
            result.setText("Product = " + (a * b));
        });

        div.setOnClickListener(v -> {
            double a = Double.parseDouble(num1.getText().toString());
            double b = Double.parseDouble(num2.getText().toString());

            if (b == 0) {
                result.setText("Cannot divide by zero");
            } else {
                result.setText("Division = " + (a / b));
            }
        });


    }
}