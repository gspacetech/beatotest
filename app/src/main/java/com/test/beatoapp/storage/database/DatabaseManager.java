package com.test.beatoapp.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static DatabaseManager sInstance;
    private static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "beato.db";
    private UserTestTable userTestTable;
    private Context context;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }
        sInstance.context = context;
        return sInstance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        getUserTestTable().createTable(db);
    }

    public UserTestTable getUserTestTable() {
        if (userTestTable == null) {
            userTestTable = new UserTestTable(this);
        }
        return userTestTable;
    }
}
