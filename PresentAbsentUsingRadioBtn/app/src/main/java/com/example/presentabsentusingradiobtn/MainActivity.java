package com.example.presentabsentusingradiobtn;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tvStudent;
    RadioButton rbPresent,rbAbsent;
    RadioGroup rg;
    Button btnSubmit;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String status = "";
        if(item.getItemId() == R.id.menu_save){

            if(rbPresent.isChecked()){
                status = "Present";
            }
            if(rbAbsent.isChecked()){
                status = "Absent";
            }

            Toast.makeText(this, "Status saved"+status, Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId() == R.id.menu_clear){
            rg.clearCheck();

        }
        if(item.getItemId() == R.id.menu_view){
            StringBuilder result = new StringBuilder();
            if(rbPresent.isChecked()){
                result.append("Student is Present");
            }
            else{
                result.append("Student is Absent");
            }

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("result",result.toString());
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvStudent = findViewById(R.id.tvStudent);
        rbPresent = findViewById(R.id.rbPresent);
        rbAbsent = findViewById(R.id.rbAbsent);
        btnSubmit = findViewById(R.id.btnSubmit);
        rg = findViewById(R.id.rg);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                if(rbPresent.isChecked()){
                    result.append("Student is Present");
                }
                else{
                    result.append("Student is Absent");
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("result",result.toString());
                startActivity(intent);

            }
        });



    }
}