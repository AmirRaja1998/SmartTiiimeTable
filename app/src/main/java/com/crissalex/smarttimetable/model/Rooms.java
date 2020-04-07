package com.crissalex.smarttimetable.model;

public class Rooms {
    String RoomId;
    String Roomname;
    public Rooms(String string, String s, String s1){
    }
    public Rooms(String RoomId , String Roomname){

        this.RoomId = RoomId;
        this.Roomname = Roomname;
    }

    public String getRoomId() {
        return RoomId;
    }

    public String getRoomname() {
        return Roomname;
    }
}

