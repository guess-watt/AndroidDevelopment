package com.example.chessclock;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GameHistoryActivity extends AppCompatActivity {

    GridView historyGridView;
    Button backHomeButton;

    DatabaseHelper databaseHelper;

    ArrayList<GameRecord> gameRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the default "Chess Clock" heading
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_game_history);

        historyGridView = findViewById(R.id.historyGridView);
        backHomeButton = findViewById(R.id.backHomeButton);

        databaseHelper = new DatabaseHelper(this);

        gameRecords = new ArrayList<>();

        backHomeButton.setOnClickListener(v -> {
            finish();
        });

        loadHistory();
    }

    private void loadHistory() {

        Cursor cursor = databaseHelper.getAllGames();

        while (cursor.moveToNext()) {

            GameRecord record = new GameRecord();

            record.id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

            record.player1 = cursor.getString(
                    cursor.getColumnIndexOrThrow("player1")
            );

            record.player2 = cursor.getString(
                    cursor.getColumnIndexOrThrow("player2")
            );

            record.initialTime = cursor.getInt(
                    cursor.getColumnIndexOrThrow("initial_time")
            );

            record.increment = cursor.getInt(
                    cursor.getColumnIndexOrThrow("increment")
            );

            record.timeControl = cursor.getString(
                    cursor.getColumnIndexOrThrow("time_control")
            );

            record.winner = cursor.getString(
                    cursor.getColumnIndexOrThrow("winner")
            );

            record.startTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow("start_time")
            );

            record.endTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow("end_time")
            );

            record.totalTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow("total_time")
            );

            record.player1FinalTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow("player1_final_time")
            );

            record.player2FinalTime = cursor.getLong(
                    cursor.getColumnIndexOrThrow("player2_final_time")
            );

            gameRecords.add(record);
        }

        cursor.close();

        if (gameRecords.isEmpty()) {

            TextView emptyText = new TextView(this);

            emptyText.setText("No games played yet");
            emptyText.setTextSize(18);
            emptyText.setPadding(20, 20, 20, 20);

            addContentView(
                    emptyText,
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );

            return;
        }

        HistoryAdapter adapter = new HistoryAdapter();

        historyGridView.setAdapter(adapter);

        historyGridView.setOnItemClickListener(
                (parent, view, position, id) -> {

                    GameRecord selectedGame =
                            gameRecords.get(position);

                    showGameDetails(selectedGame);
                }
        );
    }

    private void showGameDetails(GameRecord game) {

        String details =
                "Game #" + game.id +

                        "\n\nPlayers" +
                        "\n" + game.player1 +
                        " vs " +
                        game.player2 +

                        "\n\nInitial Time" +
                        "\n" + game.initialTime +
                        " minutes" +

                        "\n\nTime Control" +
                        "\n" + game.timeControl +

                        "\n\nIncrement" +
                        "\n" + game.increment +
                        " seconds" +

                        "\n\nStarted" +
                        "\n" + formatDate(game.startTime) +

                        "\n\nFinished" +
                        "\n" + formatDate(game.endTime) +

                        "\n\nTotal Game Time" +
                        "\n" + formatDuration(game.totalTime) +

                        "\n\nFinal Time" +
                        "\n" + game.player1 +
                        ": " +
                        formatClockTime(game.player1FinalTime) +

                        "\n" + game.player2 +
                        ": " +
                        formatClockTime(game.player2FinalTime) +

                        "\n\nWinner" +
                        "\n" + game.winner;

        new AlertDialog.Builder(this)
                .setTitle("Game Details")
                .setMessage(details)
                .setPositiveButton("Close", null)
                .show();
    }

    private String formatDate(long timestamp) {

        if (timestamp == 0) {
            return "Unknown";
        }

        SimpleDateFormat format =
                new SimpleDateFormat(
                        "dd-MM-yyyy HH:mm:ss",
                        Locale.getDefault()
                );

        return format.format(
                new Date(timestamp)
        );
    }

    private String formatDuration(long milliseconds) {

        long totalSeconds = milliseconds / 1000;

        long hours = totalSeconds / 3600;

        long minutes =
                (totalSeconds % 3600) / 60;

        long seconds =
                totalSeconds % 60;

        if (hours > 0) {

            return String.format(
                    Locale.getDefault(),
                    "%02d:%02d:%02d",
                    hours,
                    minutes,
                    seconds
            );
        }

        return String.format(
                Locale.getDefault(),
                "%02d:%02d",
                minutes,
                seconds
        );
    }

    private String formatClockTime(long milliseconds) {

        long totalSeconds = milliseconds / 1000;

        long minutes = totalSeconds / 60;

        long seconds = totalSeconds % 60;

        return String.format(
                Locale.getDefault(),
                "%02d:%02d",
                minutes,
                seconds
        );
    }

    private class HistoryAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gameRecords.size();
        }

        @Override
        public Object getItem(int position) {
            return gameRecords.get(position);
        }

        @Override
        public long getItemId(int position) {
            return gameRecords.get(position).id;
        }

        @Override
        public View getView(
                int position,
                View convertView,
                ViewGroup parent) {

            if (convertView == null) {

                convertView = LayoutInflater.from(
                        GameHistoryActivity.this
                ).inflate(
                        R.layout.grid_history_item,
                        parent,
                        false
                );
            }

            TextView playersTextView =
                    convertView.findViewById(
                            R.id.playersTextView
                    );

            TextView dateTextView =
                    convertView.findViewById(
                            R.id.dateTextView
                    );

            GameRecord game =
                    gameRecords.get(position);

            playersTextView.setText(
                    game.player1 +
                            " vs " +
                            game.player2
            );

            dateTextView.setText(
                    formatDate(game.endTime)
            );

            return convertView;
        }
    }

    private static class GameRecord {

        int id;

        String player1;
        String player2;

        int initialTime;
        int increment;

        String timeControl;
        String winner;

        long startTime;
        long endTime;
        long totalTime;

        long player1FinalTime;
        long player2FinalTime;
    }
}