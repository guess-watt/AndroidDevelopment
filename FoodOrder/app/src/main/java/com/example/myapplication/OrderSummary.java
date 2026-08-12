package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;


public class OrderSummary extends AppCompatActivity {


    String name,mobile,meal,items;
    int total;


    public void onCreate(Bundle b){

        super.onCreate(b);

        setContentView(R.layout.activity_order_summary);



        TextView details=findViewById(R.id.details);

        Button invoice=findViewById(R.id.invoice);



        Intent i=getIntent();


        name=i.getStringExtra("name");
        mobile=i.getStringExtra("mobile");
        meal=i.getStringExtra("meal");
        items=i.getStringExtra("items");

        total=i.getIntExtra("total",0);



        details.setText(
                "Name : "+name+
                        "\nMobile : "+mobile+
                        "\nMeal : "+meal+
                        "\nItems : "+items+
                        "\nTotal : ₹"+total);



        invoice.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){


                Intent in=new Intent(
                        OrderSummary.this,
                        InvoiceActivity.class);



                in.putExtra("name",name);
                in.putExtra("mobile",mobile);
                in.putExtra("meal",meal);
                in.putExtra("items",items);
                in.putExtra("total",total);


                startActivity(in);


            }

        });


    }


}