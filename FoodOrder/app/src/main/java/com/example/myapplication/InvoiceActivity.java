package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;


public class InvoiceActivity extends AppCompatActivity {


    public void onCreate(Bundle b){

        super.onCreate(b);

        setContentView(R.layout.activity_invoice);



        TextView invoice=findViewById(R.id.invoiceText);



        Intent i=getIntent();


        String name=i.getStringExtra("name");
        String mobile=i.getStringExtra("mobile");
        String meal=i.getStringExtra("meal");
        String items=i.getStringExtra("items");

        int total=i.getIntExtra("total",0);



        double discount=0;



        if(total>800)
        {
            discount=total*0.15;
        }

        else if(total>=500)
        {
            discount=total*0.10;
        }



        double finalAmount=total-discount;



        invoice.setText(

                "Customer Name : "+name+
                        "\nMobile : "+mobile+
                        "\nMeal Type : "+meal+
                        "\nFood Items : "+items+
                        "\nTotal Bill : ₹"+total+
                        "\nDiscount : ₹"+discount+
                        "\nFinal Amount : ₹"+finalAmount+
                        "\n\nThank You for Visiting Our Restaurant"

        );



    }

}