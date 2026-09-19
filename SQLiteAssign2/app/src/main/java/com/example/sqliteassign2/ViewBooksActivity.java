package com.example.sqliteassign2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewBooksActivity extends AppCompatActivity {

    TextView bookDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_books);

        bookDetails = findViewById(R.id.bookDetails);

        databaseHelper = new DatabaseHelper(this);

        displayBooks();
    }

    private void displayBooks() {

        Cursor cursor = databaseHelper.getAllBooks();

        StringBuilder data = new StringBuilder();

        if (cursor.getCount() == 0) {

            bookDetails.setText("No books found.");

            cursor.close();

            return;
        }

        while (cursor.moveToNext()) {

            int bookId = cursor.getInt(0);
            String bookName = cursor.getString(1);
            String authorName = cursor.getString(2);
            String publisherName = cursor.getString(3);
            int year = cursor.getInt(4);
            double price = cursor.getDouble(5);
            int stock = cursor.getInt(6);

            data.append("Book ID: ")
                    .append(bookId)
                    .append("\n");

            data.append("Book Name: ")
                    .append(bookName)
                    .append("\n");

            data.append("Author Name: ")
                    .append(authorName)
                    .append("\n");

            data.append("Publisher Name: ")
                    .append(publisherName)
                    .append("\n");

            data.append("Year: ")
                    .append(year)
                    .append("\n");

            data.append("Price: ")
                    .append(price)
                    .append("\n");

            data.append("Stock: ")
                    .append(stock)
                    .append("\n");

            data.append("-------------------------\n\n");
        }

        cursor.close();

        bookDetails.setText(data.toString());
    }
}