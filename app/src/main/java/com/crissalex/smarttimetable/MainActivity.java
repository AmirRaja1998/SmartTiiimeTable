package com.crissalex.smarttimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editTextTeacher, editTextCourse, editTextRoom, editTextSlot;
    Button buttonAdd;
    DatabaseReference databaseTeachers;
    DatabaseReference databaseCourses;
    DatabaseReference databaseRooms;
    DatabaseReference databaseSlots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseTeachers = FirebaseDatabase.getInstance().getReference("teachers");
        databaseCourses = FirebaseDatabase.getInstance().getReference("courses");
        databaseRooms = FirebaseDatabase.getInstance().getReference("rooms");
        databaseSlots = FirebaseDatabase.getInstance().getReference("slots");

        editTextTeacher = (EditText) findViewById(R.id.editTextTeacher);
        buttonAdd = (Button) findViewById(R.id.buttonAddTeachers);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeachers();

            }
        });
        editTextCourse = (EditText) findViewById(R.id.editTextCourse);
        buttonAdd = (Button) findViewById(R.id.buttonAddCourses);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourses();

            }
        });

        editTextRoom = (EditText) findViewById(R.id.editTextRoom);
        buttonAdd = (Button) findViewById(R.id.buttonAddRoom);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoom();

            }
        });

        editTextSlot = (EditText) findViewById(R.id.editTextSlot);
        buttonAdd = (Button) findViewById(R.id.buttonAddSlot);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSlot();

            }
        });
    }

    private void addCourses() {
        String name = editTextCourse.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseCourses.push().getKey();
            Courses courses = new Courses(id, name);
            databaseCourses.child(id).setValue(courses)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });

        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private void addRoom() {
        String name = editTextRoom.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            String id = databaseRooms.push().getKey();
            Rooms rooms = new Rooms(id, name);
            databaseRooms.child(id).setValue(rooms);

            Toast.makeText(this, "Rooms added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Should enter a Room", Toast.LENGTH_LONG).show();
        }
    }

    private void addSlot() {
        String name = editTextSlot.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseSlots.push().getKey();
            Slots slot = new Slots(id, name);
            databaseTeachers.child(id).setValue(slot);
            Toast.makeText(this, "Slot added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }


    private void addTeachers() {

        String name = editTextTeacher.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseTeachers.push().getKey();
            Teacher teacher = new Teacher(id, name);
            databaseTeachers.child(id).setValue(teacher)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(MainActivity.this, "Teacher added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}

