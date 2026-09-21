package com.example.chessclock;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChessClockActivity extends AppCompatActivity {

    TextView player1Button;
    TextView player2Button;

    Button resetButton;
    Button playPauseButton;
    Button stopButton;

    CountDownTimer player1Timer;
    CountDownTimer player2Timer;

    long player1Time;
    long player2Time;
    long initialTime;

    long gameStartTime;

    boolean player1Turn = true;
    boolean gamePaused = false;
    boolean gameOver = false;
    boolean gameSaved = false;

    boolean wasPausedBeforeStop = false;

    String player1;
    String player2;

    int timeMinutes;
    int increment = 0;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chess_clock);

        player1Button = findViewById(R.id.player1Button);
        player2Button = findViewById(R.id.player2Button);

        resetButton = findViewById(R.id.resetButton);
        playPauseButton = findViewById(R.id.playPauseButton);
        stopButton = findViewById(R.id.stopButton);

        player1 = getIntent().getStringExtra("player1");
        player2 = getIntent().getStringExtra("player2");

        timeMinutes = getIntent().getIntExtra(
                "timeMinutes",
                5
        );

        initialTime = timeMinutes * 60 * 1000L;

        player1Time = initialTime;
        player2Time = initialTime;

        gameStartTime = System.currentTimeMillis();

        databaseHelper = new DatabaseHelper(this);

        updateButtons();

        startPlayer1Timer();

        player1Button.setOnClickListener(v -> {

            if (player1Turn && !gamePaused && !gameOver) {
                switchToPlayer2();
            }
        });

        player2Button.setOnClickListener(v -> {

            if (!player1Turn && !gamePaused && !gameOver) {
                switchToPlayer1();
            }
        });

        resetButton.setOnClickListener(v -> showResetDialog());

        playPauseButton.setOnClickListener(v -> {

            if (gameOver) {
                return;
            }

            if (gamePaused) {
                resumeGame();
            } else {
                pauseGame();
            }
        });

        stopButton.setOnClickListener(v -> showStopDialog());
    }

    private void startPlayer1Timer() {

        player1Turn = true;

        player1Timer = new CountDownTimer(
                player1Time,
                1000
        ) {

            @Override
            public void onTick(long millisUntilFinished) {

                player1Time = millisUntilFinished;

                updateButtons();
            }

            @Override
            public void onFinish() {

                player1Time = 0;

                updateButtons();

                gameOver = true;

                // Player 2 wins when Player 1's time expires
                saveGame(
                        player2,
                        "Time expired"
                );

                showTimeExpiredDialog(player2);
            }

        }.start();
    }

    private void startPlayer2Timer() {

        player1Turn = false;

        player2Timer = new CountDownTimer(
                player2Time,
                1000
        ) {

            @Override
            public void onTick(long millisUntilFinished) {

                player2Time = millisUntilFinished;

                updateButtons();
            }

            @Override
            public void onFinish() {

                player2Time = 0;

                updateButtons();

                gameOver = true;

                // Player 1 wins when Player 2's time expires
                saveGame(
                        player1,
                        "Time expired"
                );

                showTimeExpiredDialog(player1);
            }

        }.start();
    }

    private void switchToPlayer2() {

        if (player1Timer != null) {
            player1Timer.cancel();
        }

        startPlayer2Timer();
    }

    private void switchToPlayer1() {

        if (player2Timer != null) {
            player2Timer.cancel();
        }

        startPlayer1Timer();
    }

    private void pauseGame() {

        if (player1Turn && player1Timer != null) {
            player1Timer.cancel();
        }

        if (!player1Turn && player2Timer != null) {
            player2Timer.cancel();
        }

        gamePaused = true;

        playPauseButton.setText("Play");
    }

    private void resumeGame() {

        if (gameOver) {
            return;
        }

        gamePaused = false;

        playPauseButton.setText("Pause");

        if (player1Turn) {
            startPlayer1Timer();
        } else {
            startPlayer2Timer();
        }
    }

    private void showResetDialog() {

        new AlertDialog.Builder(this)
                .setTitle("Reset Game")
                .setMessage(
                        "Are you sure you want to reset the game?"
                )
                .setPositiveButton(
                        "Reset",
                        (dialog, which) -> resetGame()
                )
                .setNegativeButton(
                        "Cancel",
                        null
                )
                .show();
    }

    private void resetGame() {

        if (player1Timer != null) {
            player1Timer.cancel();
        }

        if (player2Timer != null) {
            player2Timer.cancel();
        }

        player1Time = initialTime;
        player2Time = initialTime;

        player1Turn = true;
        gamePaused = false;
        gameOver = false;
        gameSaved = false;

        gameStartTime = System.currentTimeMillis();

        playPauseButton.setText("Pause");

        updateButtons();

        startPlayer1Timer();
    }

    private void showStopDialog() {

        if (gameOver) {
            return;
        }

        wasPausedBeforeStop = gamePaused;

        if (player1Timer != null) {
            player1Timer.cancel();
        }

        if (player2Timer != null) {
            player2Timer.cancel();
        }

        gamePaused = true;

        String[] players = {
                player1,
                player2,
                "Draw"
        };

        new AlertDialog.Builder(this)
                .setTitle("Who Won?")
                .setSingleChoiceItems(
                        players,
                        -1,
                        (dialog, which) -> {

                            String winner = players[which];

                            dialog.dismiss();

                            showConfirmWinnerDialog(winner);
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        (dialog, which) -> {

                            if (wasPausedBeforeStop) {
                                gamePaused = true;
                                playPauseButton.setText("Play");
                            } else {
                                resumeGame();
                            }
                        }
                )
                .show();
    }

    private void showConfirmWinnerDialog(String winner) {

        new AlertDialog.Builder(this)
                .setTitle("Finish Game")
                .setMessage(
                        "Winner: " + winner +
                                "\n\nSave this game?"
                )
                .setPositiveButton(
                        "Save",
                        (dialog, which) -> {

                            gameOver = true;

                            String resultType;

                            if (winner.equals("Draw")) {
                                resultType = "Draw";
                            } else {
                                resultType = "Game stopped manually";
                            }

                            saveGame(
                                    winner,
                                    resultType
                            );

                            finish();
                        }
                )
                .setNegativeButton(
                        "Cancel",
                        (dialog, which) -> {

                            if (wasPausedBeforeStop) {
                                gamePaused = true;
                                playPauseButton.setText("Play");
                            } else {
                                resumeGame();
                            }
                        }
                )
                .show();
    }

    private void showTimeExpiredDialog(String winner) {

        new AlertDialog.Builder(this)
                .setTitle("Time Expired")
                .setMessage(
                        winner +
                                " won!\n\n" +
                                "The game was saved automatically."
                )
                .setCancelable(false)
                .setNegativeButton(
                        "Return Home",
                        (dialog, which) -> {

                            Intent intent = new Intent(
                                    ChessClockActivity.this,
                                    MainActivity.class
                            );

                            intent.addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                            Intent.FLAG_ACTIVITY_SINGLE_TOP
                            );

                            startActivity(intent);

                            finish();
                        }
                )
                .setPositiveButton(
                        "Rematch",
                        (dialog, which) -> {

                            Intent intent = new Intent(
                                    ChessClockActivity.this,
                                    ChessClockActivity.class
                            );

                            intent.putExtra(
                                    "player1",
                                    player1
                            );

                            intent.putExtra(
                                    "player2",
                                    player2
                            );

                            intent.putExtra(
                                    "timeMinutes",
                                    timeMinutes
                            );

                            startActivity(intent);

                            finish();
                        }
                )
                .show();
    }

    private void saveGame(
            String winner,
            String resultType) {

        if (gameSaved) {
            return;
        }

        gameSaved = true;

        long endTime = System.currentTimeMillis();

        long totalTime =
                endTime - gameStartTime;

        String timeControl =
                timeMinutes + " + " + increment;

        databaseHelper.insertGame(
                player1,
                player2,
                timeControl,
                timeMinutes,
                increment,
                winner,
                resultType,
                gameStartTime,
                endTime,
                totalTime,
                player1Time,
                player2Time
        );
    }

    private void updateButtons() {

        player1Button.setText(
                player1 +
                        "\n" +
                        formatTime(player1Time)
        );

        player2Button.setText(
                player2 +
                        "\n" +
                        formatTime(player2Time)
        );
    }

    private String formatTime(long milliseconds) {

        long totalSeconds =
                milliseconds / 1000;

        long minutes =
                totalSeconds / 60;

        long seconds =
                totalSeconds % 60;

        return String.format(
                "%02d:%02d",
                minutes,
                seconds
        );
    }

    @Override
    protected void onDestroy() {

        if (player1Timer != null) {
            player1Timer.cancel();
        }

        if (player2Timer != null) {
            player2Timer.cancel();
        }

        super.onDestroy();
    }
}