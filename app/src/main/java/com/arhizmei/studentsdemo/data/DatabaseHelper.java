package com.arhizmei.studentsdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.arhizmei.studentsdemo.model.Student;
import com.arhizmei.studentsdemo.utils.Util;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.TABLE_NAME, null, Util.TABLE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY,"+
                Util.TABLE_NAME + " TEXT," +
                Util.KEY_SURNAME + " TEXT," +
                Util.KEY_UNIVERSITY + " TEXT,"+
                Util.KEY_AVERAGE_GRADE + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+ Util.TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_UNIVERSITY, student.getUniversity());
        contentValues.put(Util.KEY_AVERAGE_GRADE, student.getAverageGrade());

        database.insert(Util.TABLE_NAME, null, contentValues);
        database.close();
    }

}
