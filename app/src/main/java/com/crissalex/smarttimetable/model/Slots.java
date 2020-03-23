package com.crissalex.smarttimetable.model;

public class Slots {
    String Slotid;
    String Slotname;
    public Slots(){
    }
    public Slots(String Slotid , String Slotname){

        this.Slotid = Slotid;
        this.Slotname = Slotname;
    }

    public String getSlotid() {
        return Slotid;
    }

    public String getSlotname() {
        return Slotname;
    }
}
