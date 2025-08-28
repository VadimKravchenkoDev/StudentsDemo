package com.arhizmei.studentsdemo.model;

public class Student {
    private int id;
    private String name;
    private String surname;
    private String university;
    private String averageGrade;


    public Student(String averageGrade, String university, String surname, String name) {
        this.averageGrade = averageGrade;
        this.university = university;
        this.surname = surname;
        this.name = name;
    }

    public Student(int id, String name, String surname, String university, String averageGrade) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.university = university;
        this.averageGrade = averageGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }
}
