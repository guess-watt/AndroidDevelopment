package com.example.myapplication;


import android.content.*;
import android.os.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class ConsultationActivity extends AppCompatActivity {


    RadioGroup dept;


    public void onCreate(Bundle b)
    {
        super.onCreate(b);

        setContentView(R.layout.activity_consultation);


        dept=findViewById(R.id.dept);


        Button calc=findViewById(R.id.calc);



        calc.setOnClickListener(v->{


            int fee=0;

            String department="";


            int id=dept.getCheckedRadioButtonId();


            RadioButton r=findViewById(id);

            department=r.getText().toString();



            if(id== R.id.cardio)
                fee=1000;

            else if(id==R.id.dental)
                fee=700;

            else
                fee=500;



            Intent old=getIntent();


            Intent i=new Intent(
                    ConsultationActivity.this,
                    com.example.myapplication.BillActivity.class);



            i.putExtra("pid",
                    old.getStringExtra("pid"));

            i.putExtra("name",
                    old.getStringExtra("name"));

            i.putExtra("service",
                    old.getStringExtra("service"));

            i.putExtra("department",
                    department);


            i.putExtra("fee",fee);


            startActivity(i);



        });

    }

}