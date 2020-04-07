package com.crissalex.smarttimetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crissalex.smarttimetable.model.Courses;
import com.crissalex.smarttimetable.model.Rooms;
import com.crissalex.smarttimetable.model.Semesters;
import com.crissalex.smarttimetable.model.Slots;
import com.crissalex.smarttimetable.model.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText editTextTeacher, editTextCourse, editTextRoom, editTextSlot , editTextSemester;
    Button buttonAdd;
    DatabaseReference databaseTeachers;
    DatabaseReference databaseCourses;
    DatabaseReference databaseRooms;
    DatabaseReference databaseSlots;
    DatabaseReference databaseSemesters;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseTeachers = FirebaseDatabase.getInstance().getReference("teachers");
        databaseCourses = FirebaseDatabase.getInstance().getReference("courses");
        databaseRooms = FirebaseDatabase.getInstance().getReference("rooms");
        databaseSlots = FirebaseDatabase.getInstance().getReference("slots");
        databaseSlots = FirebaseDatabase.getInstance().getReference("Semester");

        buttonAdd = (Button) findViewById(R.id.buttonAddTeachers);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeachers();

            }
        });


        buttonAdd = (Button) findViewById(R.id.buttonAddCourses);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourses();

            }
        });


        buttonAdd = (Button) findViewById(R.id.buttonAddRoom);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRoom();

            }
        });


        buttonAdd = (Button) findViewById(R.id.buttonAddSlot);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSlot();

            }
        });

        buttonAdd = (Button) findViewById(R.id.buttonAddSemester);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSemester();

            }
        });

    }

    private void addSemester() {
        Log.d(TAG, "addSemesters:");
        String name = editTextCourse.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseSemesters.push().getKey();
            Semesters semesters = new Semesters(id, name);
            databaseSemesters.child(id).setValue(semesters)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });

        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }


    private void addCourses() {
        Log.d(TAG, "addCourses: ");
        String name = editTextCourse.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseCourses.push().getKey();
            Courses courses = new Courses(id, name);
            databaseCourses.child(id).setValue(courses)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    });

        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }

    private void addRoom() {
        Log.d(TAG, "addRoom: ");
        String name = editTextRoom.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {
            String id = databaseRooms.push().getKey();
            Rooms rooms = new Rooms(id, name);
            databaseRooms.child(id).setValue(rooms)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                        }
                    });

            Toast.makeText(this, "Rooms added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You Should enter a Room", Toast.LENGTH_LONG).show();
        }
    }

    private void addSlot() {
        Log.d(TAG, "addSlot: ");
        String name = editTextSlot.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseSlots.push().getKey();
            Slots slot = new Slots(id, name);
            databaseSlots.child(id).setValue(slot)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: ");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                        }
                    });
        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }


    private void addTeachers() {
        Log.d(TAG, "addTeachers: ");
        String name = editTextTeacher.getText().toString().trim();
        if (!TextUtils.isEmpty(name)) {

            String id = databaseTeachers.push().getKey();
            Teacher teacher = new Teacher(id, name);
            databaseTeachers.child(id).setValue(teacher)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: ");
                            Toast.makeText(MainActivity.this, "Teacher added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.getMessage());
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(this, "You Should enter a name", Toast.LENGTH_LONG).show();
        }
    }
}

