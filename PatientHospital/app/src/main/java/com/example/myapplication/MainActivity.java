package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {


    EditText pid,name,age;
    RadioGroup gender;

    CheckBox checkup,test,scan;


    public void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Activity Started", Toast.LENGTH_SHORT).show();

        pid=findViewById(R.id.pid);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);

        gender=findViewById(R.id.gender);

        checkup=findViewById(R.id.checkup);
        test=findViewById(R.id.test);
        scan=findViewById(R.id.scan);


        Button next=findViewById(R.id.next);



        next.setOnClickListener(v->{


            if(pid.getText().toString().isEmpty() ||
                    name.getText().toString().isEmpty())
            {
                Toast.makeText(this,
                        "Enter Details",
                        Toast.LENGTH_SHORT).show();

                return;
            }



            String g="";

            int id=gender.getCheckedRadioButtonId();

            if(id!=-1)
            {
                RadioButton r=findViewById(id);
                g=r.getText().toString();
            }


            String service="";

            if(checkup.isChecked())
                service+="Checkup ";

            if(test.isChecked())
                service+="Blood Test ";

            if(scan.isChecked())
                service+="Scan ";



            Intent i=new Intent(
                    MainActivity.this,
                    ConsultationActivity.class);


            i.putExtra("pid",pid.getText().toString());
            i.putExtra("name",name.getText().toString());
            i.putExtra("age",age.getText().toString());
            i.putExtra("gender",g);
            i.putExtra("service",service);


            startActivity(i);


        });


    }



    // OPTIONS MENU

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id=item.getItemId();


        if(id==R.id.exit)
        {
            finish();
        }

        else if(id==R.id.newreg)
        {
            recreate();
        }

        else if(id==R.id.home)
        {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }


        return true;

    }

}