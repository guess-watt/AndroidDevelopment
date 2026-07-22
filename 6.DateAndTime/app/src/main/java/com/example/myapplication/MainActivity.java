package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView date1,date2,time1,time2;
    Button result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        date1 = findViewById(R.id.tvdate1);
        date2 = findViewById(R.id.tvdate2);
        time1 = findViewById(R.id.tvtime1);
        time2 = findViewById(R.id.tvtime2);

        result = findViewById(R.id.btnshow);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date currentDate = new Date();

                SimpleDateFormat d1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                SimpleDateFormat d2 = new SimpleDateFormat("EEEE,MMMM dd,yyyy",Locale.getDefault());

                SimpleDateFormat t1 = new SimpleDateFormat("HH:mm:ss",Locale.getDefault());
                SimpleDateFormat t2 = new SimpleDateFormat("hh:mm:ss a",Locale.getDefault());

                date1.setText("Date Format 1: "+d1.format(currentDate));
                date2.setText("Date format 2: "+d2.format(currentDate));

                time1.setText("Time format 1: "+t1.format(currentDate));
                time2.setText("Time format 2: "+t2.format(currentDate));
            }
        });


    }
}