package com.semesta.icnema_uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cinema.db";
    private static final int DATABASE_VERSION = 4; // Incremented version
    public static final String TABLE_NAME = "booked_movies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_TIME = "time"; // Add time column

    private static final String CREATE_TABLE_BOOKED_MOVIES = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_OVERVIEW + " TEXT, "
            + COLUMN_RELEASE_DATE + " TEXT, "
            + COLUMN_TIME + " TEXT, " // Add time column
            + "seats TEXT, "
            + "price INTEGER, "
            + "image_url TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKED_MOVIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN price INTEGER");
        }
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN image_url TEXT");
        }
        if (oldVersion < 4) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN time TEXT"); // Add time column
        }
    }

    public void insertBooking(String title, String overview, String releaseDate, String time, String seats, int price, String imageUrl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_OVERVIEW, overview);
        values.put(COLUMN_RELEASE_DATE, releaseDate);
        values.put(COLUMN_TIME, time); // Add time value
        values.put("seats", seats);
        values.put("price", price);
        values.put("image_url", imageUrl);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean isSeatBooked(String seat, String releaseDate, String time) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE seats LIKE ? AND release_date = ? AND time = ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + seat + "%", releaseDate, time});
        boolean isBooked = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isBooked;
    }
}