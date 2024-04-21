package com.example.crud_operations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "course.db";

    private static final int DB_VERSION = 1;
    private static final String ADMIN_TABLE = "admin";
    private static final String ADMIN_DEPT = "dept";
    private static final String ADMIN_ID = "id";
    private static final String ADMIN_ADDRESS = "address";
    private  static  final String TEACHER_TABLE = "teacher";
    private  static  final String   ID = "id";
    private  static  final String NAME = "name";
    private  static  final String Address = "address";
    private static final String TABLE_NAME = "mycourses";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String DURATION_COL = "duration";

    private static final String DESCRIPTION_COL = "description";

    private static final String TRACKS_COL = "tracks";

    // creating a constructor for our database handler.
    public  DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";

        String query1 = "CREATE TABLE " +  TEACHER_TABLE + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT,"
                + Address + " TEXT)";

        String query2 = "CREATE TABLE " +  ADMIN_TABLE + " ("
                + ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ADMIN_DEPT + " TEXT,"
                + ADMIN_ADDRESS + " TEXT)";

        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + NAME_COL + ", " + DURATION_COL + ", " + DESCRIPTION_COL + ", " + TRACKS_COL + ") VALUES ('Shubhvrat', '15-hours', 'MCA', 'NaN')");
        db.execSQL("INSERT INTO " + TEACHER_TABLE + " (" + NAME + ", " + Address + ") VALUES ('prof. Sudarshan sirsat', 'Vidyavihar' )");
        db.execSQL("INSERT INTO " + ADMIN_TABLE + " (" + ADMIN_DEPT + ", " + ADMIN_ADDRESS + ") VALUES ('Data science dept', 'Vaashi' )");

    }

    public Cursor ViewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME , null);

        return cursor;

    }

    public Cursor teacherData()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery("select * from " + TEACHER_TABLE , null);
        return cursor1;
    }

    public Cursor adminData()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor2 = sqLiteDatabase.rawQuery("select * from " + ADMIN_TABLE , null);
        return  cursor2;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add code to handle database upgrades if needed

    }
}

