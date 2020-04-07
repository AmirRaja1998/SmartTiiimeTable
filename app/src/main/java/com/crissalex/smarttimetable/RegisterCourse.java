package com.crissalex.smarttimetable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crissalex.smarttimetable.model.Courses;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterCourse extends AppCompatActivity {
    DatabaseReference databaseCourses;
    EditText course_id, course_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        setTitle("Register Course");

        databaseCourses = FirebaseDatabase.getInstance().getReference("Courses");
        course_id = findViewById(R.id.course_id);
        course_name = findViewById(R.id.course_name);

        Button registerCourse = (Button) findViewById(R.id.register_course);
        registerCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getString(course_id)) || TextUtils.isEmpty(getString(course_name))){
                    Toast.makeText(RegisterCourse.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    registerCourse();
                }

            }
        });
    }
    private void registerCourse() {
        final Courses courses = new Courses(getString(course_id), getString(course_name), "12345");
        databaseCourses.child(getString(course_id))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(RegisterCourse.this, "Course Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseCourses.child(getString(course_id))
                                    .setValue(courses)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterCourse.this, "Course Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterCourse.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

