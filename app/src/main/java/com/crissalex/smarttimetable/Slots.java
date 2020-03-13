package com.crissalex.smarttimetable;

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
