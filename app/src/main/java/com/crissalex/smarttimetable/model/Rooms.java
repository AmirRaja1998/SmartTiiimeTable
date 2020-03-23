package com.crissalex.smarttimetable.model;

public class Rooms {
    String RoomId;
    String Roomname;
    public Rooms(){
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

