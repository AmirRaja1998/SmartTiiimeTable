package com.crissalex.smarttimetable;

public class ReadTeacher {
    private int teacherid;
    private String teachername;

    public ReadTeacher() {
    }

    public ReadTeacher(int teacherid, String teachername) {
        this.teacherid = teacherid;
        this.teachername = teachername;
    }

    public int getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
}
