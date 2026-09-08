    package com.example.myapplication;

    import static android.provider.ContactsContract.Intents.Insert.NOTES;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DB_NAME = "app_database.db";
        private static final int DB_VERSION = 1;

        public static final String TABLE_NOTES = "Student_Details";
        public static final String COL_ID = "student_id";
        public static final String COL_TITLE = "student_name";
        public static final String COL_CONTENT = "student_mark";

        public DatabaseHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE "+TABLE_NOTES+" ("+
                    COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COL_TITLE+ " TEXT NOT NULL, "+
                    COL_CONTENT+ " INTEGER)" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTES);
            onCreate(db);

        }

        //Insert a new node
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValue values = new ContextValues();
        values.put(DatabaseHelper.COL_TITLE, "Shopping List");
        values.put(DatabaseHelper.COL_CONTENT,"Milk, eggs, bread");
        long newId = db.insert(DatabaseHelper.TABLE_NOTES, null, values);

        //Read all notes back
        Cursor cursor = db.query(DatabaseHelper.TABLE_NOTES,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                String title = cursor.getString(
                        cursor.getColumnIndexOrThrow(DatabaseHelper.COL_TITLE);
                );
            }
        }

        Public boolen insertStudent(name,mark){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COL_TITLE,"Shopping List");
            values.put(DatabaseHelper.COL_NAME, name);
            values.put(DatabaseHelper.COL_MARK, mark);
            long newId = db.insert(DatabaseHelper.TABLE_NOTES, null, values);

            //Read all notes back
            Public boolen
        }




    }
