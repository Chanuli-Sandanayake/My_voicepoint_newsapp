package com.example.voicepoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "VoicePoint.db";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the users table with username, password, and email
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT, email TEXT)");

        // Optional: Add a default test user
        db.execSQL("INSERT INTO users VALUES('admin', '1234', 'admin@example.com')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // Check if username and password match
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",
                new String[]{username, password});
        return cursor.getCount() > 0;
    }

    //Check if username already exists(signup)
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        return cursor.getCount() > 0;
    }

    // Insert new user with username, password, and email(signup)
    public Boolean insertUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("email", email);
        long result = db.insert("users", null, values);
        return result != -1;
    }

    public String getEmailByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT email FROM users WHERE username=?", new String[]{username});
        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        }
        return null;
    }
    public boolean updateUser(String oldUsername, String newUsername, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", newUsername);
        values.put("email", newEmail);
        int result = db.update("users", values, "username=?", new String[]{oldUsername});
        return result > 0;
    }
}
