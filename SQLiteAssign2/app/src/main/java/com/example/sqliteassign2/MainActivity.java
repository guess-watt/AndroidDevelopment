package com.example.sqliteassign2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText bookId, bookName, authorName, publisherName;
    EditText year, price, stock;

    Button addButton, viewButton;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bookId = findViewById(R.id.bookId);
        bookName = findViewById(R.id.bookName);
        authorName = findViewById(R.id.authorName);
        publisherName = findViewById(R.id.publisherName);
        year = findViewById(R.id.year);
        price = findViewById(R.id.price);
        stock = findViewById(R.id.stock);

        addButton = findViewById(R.id.addButton);
        viewButton = findViewById(R.id.viewButton);

        databaseHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(v -> {

            String id = bookId.getText().toString();
            String name = bookName.getText().toString();
            String author = authorName.getText().toString();
            String publisher = publisherName.getText().toString();
            String bookYear = year.getText().toString();
            String bookPrice = price.getText().toString();
            String bookStock = stock.getText().toString();

            if (id.isEmpty() || name.isEmpty() ||
                    author.isEmpty() || publisher.isEmpty() ||
                    bookYear.isEmpty() || bookPrice.isEmpty() ||
                    bookStock.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Please enter all details",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            int idValue = Integer.parseInt(id);
            int yearValue = Integer.parseInt(bookYear);
            double priceValue = Double.parseDouble(bookPrice);
            int stockValue = Integer.parseInt(bookStock);

            boolean inserted = databaseHelper.insertBook(
                    idValue,
                    name,
                    author,
                    publisher,
                    yearValue,
                    priceValue,
                    stockValue
            );

            if (inserted) {

                Toast.makeText(
                        MainActivity.this,
                        "Book added successfully",
                        Toast.LENGTH_SHORT
                ).show();

                bookId.setText("");
                bookName.setText("");
                authorName.setText("");
                publisherName.setText("");
                year.setText("");
                price.setText("");
                stock.setText("");

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "Failed to add book",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        viewButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ViewBooksActivity.class
            );

            startActivity(intent);
        });
    }
}