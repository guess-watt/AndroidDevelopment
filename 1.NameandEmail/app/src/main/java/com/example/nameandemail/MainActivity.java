package com.example.nameandemail;

import android.os.Bundle;
import android.view.View;import android.widget.Button;
import android.widget.EditText;import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText mail;
    Button submit;
    TextView answer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.et1);
        mail = (EditText) findViewById(R.id.et2);
        submit = (Button) findViewById(R.id.btnSumbit);
        answer = (TextView) findViewById(R.id.tv1);


        submit.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String mail1 = mail.getText().toString();
                answer.setText("Name: "+name1 +"\nEmail: "+mail1);

            }});

    }
}