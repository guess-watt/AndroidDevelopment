package com.example.sqliteassign1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewStudentsActivity extends AppCompatActivity {

    TextView studentDetails;

    com.example.sqliteassign1.DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_students);

        studentDetails = findViewById(R.id.studentDetails);

        databaseHelper = new com.example.sqliteassign1.DatabaseHelper(this);

        displayStudents();
    }

    private void displayStudents() {

        Cursor cursor = databaseHelper.getAllStudents();

        StringBuilder data = new StringBuilder();

        if (cursor.getCount() == 0) {

            studentDetails.setText("No students found.");

            cursor.close();

            return;
        }

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            data.append("ID: ")
                    .append(id)
                    .append("\n");

            data.append("Name: ")
                    .append(name)
                    .append("\n");

            data.append("Age: ")
                    .append(age)
                    .append("\n\n");
        }

        cursor.close();

        studentDetails.setText(data.toString());
    }
}