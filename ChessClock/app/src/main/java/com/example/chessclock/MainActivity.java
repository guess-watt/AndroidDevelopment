package com.example.chessclock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText player1EditText;
    EditText player2EditText;

    RadioGroup timeGroup;

    Button startButton;
    Button historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1EditText = findViewById(R.id.player1EditText);
        player2EditText = findViewById(R.id.player2EditText);

        timeGroup = findViewById(R.id.timeGroup);

        startButton = findViewById(R.id.startButton);
        historyButton = findViewById(R.id.historyButton);

        startButton.setOnClickListener(v -> {

            String player1 = player1EditText.getText().toString().trim();
            String player2 = player2EditText.getText().toString().trim();

            if (player1.isEmpty() || player2.isEmpty()) {
                Toast.makeText(
                        this,
                        R.string.error_names,
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            int selectedId = timeGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(
                        this,
                        R.string.error_time,
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            RadioButton selectedButton = findViewById(selectedId);

            int timeMinutes = Integer.parseInt(
                    selectedButton.getTag().toString()
            );

            Intent intent = new Intent(
                    MainActivity.this,
                    ChessClockActivity.class
            );

            intent.putExtra("player1", player1);
            intent.putExtra("player2", player2);
            intent.putExtra("timeMinutes", timeMinutes);

            startActivity(intent);
        });

        historyButton.setOnClickListener(v -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    GameHistoryActivity.class
            );

            startActivity(intent);
        });
    }
}