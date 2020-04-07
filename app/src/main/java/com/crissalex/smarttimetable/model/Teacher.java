package com.crissalex.smarttimetable.model;

public class Teacher {
    String TeacherId;
    String Teachername;
    public Teacher(String string, String s, String s1){
    }
    public Teacher(String TeacherId , String Teachername){

        this.TeacherId = TeacherId;
        this.Teachername = Teachername;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public String getTeachername() {
        return Teachername;
    }
}
