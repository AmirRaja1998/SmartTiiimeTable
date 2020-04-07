package com.crissalex.smarttimetable.model;

public class Courses {
    String CourseId;
    String Coursename;
    public Courses(String string, String s, String s1){
    }
    public Courses(String CourseId , String Coursename){

        this.CourseId = CourseId;
        this.Coursename = Coursename;
    }

    public String getCourseId()
    {
        return CourseId;
    }

    public String getCoursename()
    {
        return Coursename;
    }
}