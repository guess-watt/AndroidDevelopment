package com.example.chessclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ChessClock.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE games (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "player1 TEXT, " +
                        "player2 TEXT, " +
                        "initial_time INTEGER, " +
                        "increment INTEGER, " +
                        "winner TEXT, " +
                        "date INTEGER, " +
                        "time_control TEXT, " +
                        "result_type TEXT, " +
                        "start_time INTEGER, " +
                        "end_time INTEGER, " +
                        "total_time INTEGER, " +
                        "player1_final_time INTEGER, " +
                        "player2_final_time INTEGER)"
        );
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        if (oldVersion < 2) {

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN time_control TEXT DEFAULT 'Unknown'"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN result_type TEXT DEFAULT 'Unknown'"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN start_time INTEGER DEFAULT 0"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN end_time INTEGER DEFAULT 0"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN total_time INTEGER DEFAULT 0"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN player1_final_time INTEGER DEFAULT 0"
            );

            db.execSQL(
                    "ALTER TABLE games ADD COLUMN player2_final_time INTEGER DEFAULT 0"
            );
        }
    }

    public long insertGame(
            String player1,
            String player2,
            String timeControl,
            int initialTime,
            int increment,
            String winner,
            String resultType,
            long startTime,
            long endTime,
            long totalTime,
            long player1FinalTime,
            long player2FinalTime) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("player1", player1);
        values.put("player2", player2);
        values.put("time_control", timeControl);
        values.put("initial_time", initialTime);
        values.put("increment", increment);
        values.put("winner", winner);
        values.put("result_type", resultType);
        values.put("start_time", startTime);
        values.put("end_time", endTime);
        values.put("total_time", totalTime);
        values.put("player1_final_time", player1FinalTime);
        values.put("player2_final_time", player2FinalTime);
        values.put("date", endTime);

        long result = db.insert("games", null, values);

        db.close();

        return result;
    }

    public Cursor getAllGames() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM games ORDER BY id DESC",
                null
        );
    }
}