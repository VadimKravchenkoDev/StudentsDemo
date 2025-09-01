package com.arhizmei.studentsdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.arhizmei.studentsdemo.data.DatabaseHelper;
import com.arhizmei.studentsdemo.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "DB_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);
        int currentVersion = db.getReadableDatabase().getVersion();
        Log.d("DB_VERSION", "Current DB version: " + currentVersion);

        // 1. Добавляем студентов
        db.addStudent(new Student("Ivan", "Suslov", "Oxford", "4.5"));
        db.addStudent(new Student("Dima", "Ivanov", "Oxford", "4.1"));
        db.addStudent(new Student("Roma", "Petrov", "Oxford", "3.5"));
        Log.d(TAG, "Added 3 students");

        // 2. Получаем одного студента по ID
        Student s = db.getStudent(1);
        if (s != null) {
            Log.d(TAG, "Get student with ID 1: " + s.getName() + " " + s.getSurname());
        }

        // 3. Получаем всех студентов
        List<Student> allStudents = db.getAllStudents();
        for (Student student : allStudents) {
            Log.d(TAG, "Student: " + student.getId() + " " + student.getName() + " " + student.getSurname()
                    + " " + student.getUniversity() + " " + student.getAverageGrade());
        }

        // 4. Обновляем студента
        if (!allStudents.isEmpty()) {
            Student first = allStudents.get(0);
            first.setAverageGrade("5.0");
            int updated = db.updateStudent(first);
            Log.d(TAG, "Updated student ID " + first.getId() + ", rows affected: " + updated);
        }

        // 5. Удаляем студента
        if (!allStudents.isEmpty()) {
            Student last = allStudents.get(allStudents.size() - 1);
            db.deleteStudent(last);
            Log.d(TAG, "Deleted student ID " + last.getId());
        }

        // 6. Проверяем количество студентов
        int count = db.getStudentCount();
        Log.d(TAG, "Total students count: " + count);
    }
}