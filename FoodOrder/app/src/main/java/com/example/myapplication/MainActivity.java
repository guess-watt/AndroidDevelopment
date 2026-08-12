package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {


    EditText name,mobile;
    RadioGroup mealGroup;

    CheckBox pizza,burger,sandwich,drink;


    Button order;


    public void onCreate(Bundle b){

        super.onCreate(b);
        setContentView(R.layout.activity_main);


        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);

        mealGroup=findViewById(R.id.mealGroup);


        pizza=findViewById(R.id.pizza);
        burger=findViewById(R.id.burger);
        sandwich=findViewById(R.id.sandwich);
        drink=findViewById(R.id.drink);


        order=findViewById(R.id.order);



        order.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){


                String customer=name.getText().toString();
                String phone=mobile.getText().toString();



                if(customer.isEmpty())
                {
                    name.setError("Enter Name");
                    return;
                }


                if(phone.length()!=10)
                {
                    mobile.setError("Enter valid mobile number");
                    return;
                }



                int selected=mealGroup.getCheckedRadioButtonId();


                if(selected==-1)
                {
                    Toast.makeText(MainActivity.this,
                            "Select Meal Type",
                            Toast.LENGTH_SHORT).show();

                    return;
                }



                String meal="";

                if(selected==R.id.veg)
                    meal="Vegetarian";
                else
                    meal="Non-Vegetarian";




                String items="";

                int total=0;



                if(pizza.isChecked())
                {
                    items+="Pizza ";
                    total+=250;
                }


                if(burger.isChecked())
                {
                    items+="Burger ";
                    total+=150;
                }


                if(sandwich.isChecked())
                {
                    items+="Sandwich ";
                    total+=120;
                }


                if(drink.isChecked())
                {
                    items+="Soft Drink ";
                    total+=60;
                }



                if(items.equals(""))
                {
                    Toast.makeText(MainActivity.this,
                            "Select Food Item",
                            Toast.LENGTH_SHORT).show();

                    return;
                }



                Intent i=new Intent(
                        MainActivity.this,
                        com.example.myapplication.OrderSummary.class);


                i.putExtra("name",customer);
                i.putExtra("mobile",phone);
                i.putExtra("meal",meal);
                i.putExtra("items",items);
                i.putExtra("total",total);


                startActivity(i);

            }

        });

    }

}