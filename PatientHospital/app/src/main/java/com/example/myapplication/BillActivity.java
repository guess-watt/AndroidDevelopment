package com.example.myapplication;


import android.content.Intent;
import android.os.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;



public class BillActivity extends AppCompatActivity {


    public void onCreate(Bundle b)
    {

        super.onCreate(b);

        setContentView(R.layout.activity_bill);



        TextView result=findViewById(R.id.result);


        Intent i=getIntent();



        String name=i.getStringExtra("name");

        String pid=i.getStringExtra("pid");

        String service=i.getStringExtra("service");

        String dept=i.getStringExtra("department");


        int fee=i.getIntExtra("fee",0);



        String category;


        if(fee>=1000)
            category="Special Treatment";

        else
            category="Normal Treatment";



        result.setText(
                "Hospital Bill\n\n"+
                        "Patient ID : "+pid+
                        "\nName : "+name+
                        "\nDepartment : "+dept+
                        "\nServices : "+service+
                        "\nConsultation Fee : "+fee+
                        "\nCategory : "+category
        );


    }


}