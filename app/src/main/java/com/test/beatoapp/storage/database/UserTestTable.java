package com.test.beatoapp.storage.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.test.beatoapp.models.TestUserModel;
import com.test.beatoapp.storage.pref.PrefManager;
import com.test.beatoapp.utils.CommanUtils;

import java.util.ArrayList;
import java.util.List;

public class UserTestTable {

    DatabaseManager databaseHandler;

    public String TABLE_NAME = "user_test";

    public String KEY_ID = "id";
    public String KEY_NAME = "name";
    public String KEY_PHONE = "phone";
    public String KEY_EMAIL = "email";
    public String KEY_GENDER = "gender";
    public String KEY_YOB = "yob";
    public String KEY_HEIGHT = "height";
    public String KEY_WEIGHT = "weight";
    public String KEY_BMI = "bmi";
    public String KEY_READING = "reading";
    public String KEY_READING_TYPE = "reading_type";
    public String KEY_CREATED = "created";
    public String KEY_CREATED_BY = "created_by";

    public UserTestTable(DatabaseManager databaseHandler) {
        super();
        this.databaseHandler = databaseHandler;
    }

    public void createTable(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "
                + KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME+" TEXT , "
                + KEY_PHONE+" TEXT , "
                + KEY_EMAIL+" TEXT , "
                + KEY_GENDER+" TEXT , "
                + KEY_YOB+" TEXT , "
                + KEY_HEIGHT+" TEXT , "
                + KEY_WEIGHT+" TEXT , "
                + KEY_BMI+" TEXT , "
                + KEY_READING+" TEXT , "
                + KEY_READING_TYPE+" TEXT , "
                + KEY_CREATED+" TEXT , "
                + KEY_CREATED_BY+" TEXT )";

        db.execSQL(CREATION_TABLE);
    }

    public long insert(TestUserModel testUserModel) {

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, testUserModel.getName());
        values.put(KEY_PHONE, testUserModel.getPhone());
        values.put(KEY_EMAIL, testUserModel.getEmail());
        values.put(KEY_GENDER, testUserModel.getGender());
        values.put(KEY_YOB, testUserModel.getYob());
        values.put(KEY_HEIGHT, testUserModel.getHeight());
        values.put(KEY_WEIGHT, testUserModel.getWeight());
        values.put(KEY_BMI, testUserModel.getBmi());
        values.put(KEY_READING, testUserModel.getReading());
        values.put(KEY_READING_TYPE, testUserModel.getReadingType());
        values.put(KEY_CREATED, CommanUtils.getCurrentTimeStemp());
        values.put(KEY_CREATED_BY, PrefManager.getInstance().getPrefUsername());

        long last_id = db.insert(TABLE_NAME,null, values);

        Log.e("last inserted id", String.valueOf(last_id));

        db.close();

        return last_id;
    }

    public List<TestUserModel> getLast15MinRecords(){

        String selectQuery = "SELECT * FROM "+TABLE_NAME+
                " WHERE datetime(strftime('%Y-%m-%d %H:%M:%S', cast(created AS INTEGER)/ 1000, 'unixepoch', 'localtime'))" +
                " > Datetime('now', '-15 minutes','localtime')";

        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<TestUserModel> arrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                TestUserModel testUserModel = new TestUserModel();
                testUserModel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                testUserModel.setPhone(Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_PHONE))));
                testUserModel.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                testUserModel.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                testUserModel.setYob(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_YOB))));
                testUserModel.setHeight(cursor.getString(cursor.getColumnIndex(KEY_HEIGHT)));
                testUserModel.setWeight(cursor.getString(cursor.getColumnIndex(KEY_WEIGHT)));
                testUserModel.setBmi(cursor.getString(cursor.getColumnIndex(KEY_BMI)));
                testUserModel.setCreated(cursor.getString(cursor.getColumnIndex(KEY_CREATED)));
                testUserModel.setCreated_by(cursor.getString(cursor.getColumnIndex(KEY_CREATED_BY)));
                testUserModel.setReading(cursor.getString(cursor.getColumnIndex(KEY_READING)));
                testUserModel.setReadingType(cursor.getString(cursor.getColumnIndex(KEY_READING_TYPE)));
                arrayList.add(testUserModel);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return arrayList;
    }


    public TestUserModel getUserTestData(int test_id){
        String selectQuery = "SELECT * FROM "+TABLE_NAME+" where "+KEY_ID+" = "+test_id;
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        TestUserModel testUserModel = new TestUserModel();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                testUserModel.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                testUserModel.setPhone(Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_PHONE))));
                testUserModel.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                testUserModel.setGender(cursor.getString(cursor.getColumnIndex(KEY_GENDER)));
                testUserModel.setYob(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_YOB))));
                testUserModel.setHeight(cursor.getString(cursor.getColumnIndex(KEY_HEIGHT)));
                testUserModel.setWeight(cursor.getString(cursor.getColumnIndex(KEY_WEIGHT)));
                testUserModel.setBmi(cursor.getString(cursor.getColumnIndex(KEY_BMI)));
                testUserModel.setCreated(cursor.getString(cursor.getColumnIndex(KEY_CREATED)));
                testUserModel.setCreated_by(cursor.getString(cursor.getColumnIndex(KEY_CREATED_BY)));
                testUserModel.setReading(cursor.getString(cursor.getColumnIndex(KEY_READING)));
                testUserModel.setReadingType(cursor.getString(cursor.getColumnIndex(KEY_READING_TYPE)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return testUserModel;
    }

}



