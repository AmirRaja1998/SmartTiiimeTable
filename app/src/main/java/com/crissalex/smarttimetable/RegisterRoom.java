package com.crissalex.smarttimetable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crissalex.smarttimetable.model.Rooms;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterRoom extends AppCompatActivity {
    DatabaseReference databaseRooms;
    EditText room_id, room_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_room);

        setTitle("Register Room");

        databaseRooms = FirebaseDatabase.getInstance().getReference("Rooms");
        room_id = findViewById(R.id.course_id);
        room_name = findViewById(R.id.course_name);

        Button registerRoom = (Button) findViewById(R.id.register_room);
        registerRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getString(room_id)) || TextUtils.isEmpty(getString(room_name))){
                    Toast.makeText(RegisterRoom.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    registerRoom();
                }

            }
        });
    }

    private void registerRoom() {
        final Rooms rooms = new Rooms (getString(room_id), getString(room_name), "123");
        databaseRooms.child(getString(room_id))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(RegisterRoom.this, "Room Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseRooms.child(getString(room_id))
                                    .setValue(rooms)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterRoom.this, "Room Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterRoom.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private String getString(EditText editText){
        return editText.getText().toString();
    }
}
