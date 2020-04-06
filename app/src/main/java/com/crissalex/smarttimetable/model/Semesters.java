package com.crissalex.smarttimetable.model;

public class Semesters {
    String SemesterId;
    String Semestername;
    public Semesters(){
    }
    public Semesters(String SemesterId , String Semestername){

        this.SemesterId = SemesterId;
        this.Semestername = Semestername;
    }

    public String getSemesterId() {
        return SemesterId;
    }

    public String getSemestername() {
        return Semestername;
    }
}
