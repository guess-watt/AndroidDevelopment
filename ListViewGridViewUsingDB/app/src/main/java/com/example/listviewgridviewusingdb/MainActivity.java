package com.example.listviewgridviewusingdb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText et1 = findViewById(R.id.et1);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        ListView lv1 = findViewById(R.id.lv1);
        GridView gv1 = findViewById(R.id.gv1);
        databaseHelper = new DatabaseHelper(this);

        ArrayList adapter = new ArrayList();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product = et1.getText().toString();
                boolean inserted = databaseHelper{
                    product
                }
                if (inserted){
                    et1.setText("");
                }

            }
        });
    }
}