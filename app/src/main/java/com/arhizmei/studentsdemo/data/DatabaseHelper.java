package com.arhizmei.studentsdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.arhizmei.studentsdemo.model.Student;
import com.arhizmei.studentsdemo.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.TABLE_NAME, null, Util.TABLE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +
                Util.KEY_NAME + " TEXT," +
                Util.KEY_SURNAME + " TEXT," +
                Util.KEY_UNIVERSITY + " TEXT," +
                Util.KEY_AVERAGE_GRADE + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_UNIVERSITY, student.getUniversity());
        contentValues.put(Util.KEY_AVERAGE_GRADE, student.getAverageGrade());

        database.insert(Util.TABLE_NAME, null, contentValues);
        database.close();
    }

    public Student getStudent(int id) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(
                Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_SURNAME, Util.KEY_UNIVERSITY, Util.KEY_AVERAGE_GRADE},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        Student student = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                student = new Student(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
            }
            cursor.close();
        }
        database.close();
        return student;
    }

    public List<Student> getAllStudents() {
        SQLiteDatabase database = this.getReadableDatabase();

        List<Student> studentList = new ArrayList<>();

        String selectStudent = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = database.rawQuery(selectStudent, null);
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setSurname(cursor.getString(2));
                student.setUniversity(cursor.getString(3));
                student.setAverageGrade(cursor.getString(4));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return studentList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Util.KEY_NAME, student.getName());
        contentValues.put(Util.KEY_SURNAME, student.getSurname());
        contentValues.put(Util.KEY_UNIVERSITY, student.getUniversity());
        contentValues.put(Util.KEY_AVERAGE_GRADE, student.getAverageGrade());

        return database.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "+?",
                new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[]{String.valueOf(student.getId())});

        database.close();
    }
}
