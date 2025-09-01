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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.addStudent(new Student("Ivan", "Suslov", "Oxford", "4.5"));
        databaseHelper.addStudent(new Student("Dima", "Ivanov", "Oxford", "4.1"));
        databaseHelper.addStudent(new Student("Roma", "Petrov", "Oxford", "3.5"));

        List<Student> studentsList = databaseHelper.getAllStudents();

        for (Student student : studentsList) {
            Log.d("myLogSt", "Id " + String.valueOf(student.getId())
                    + "name " + student.getName()
                    + "surname " + student.getSurname()
                    + "university " + student.getUniversity()
                    + "average grade " + student.getAverageGrade());
        }
        databaseHelper.close();
    }
}