package com.crissalex.smarttimetable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crissalex.smarttimetable.model.Semesters;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterSemester extends AppCompatActivity {
    DatabaseReference databaseSemesters;
    EditText semester_id, semester_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_semester);

        setTitle("Register Semester");

        databaseSemesters = FirebaseDatabase.getInstance().getReference("Semesters");
        semester_id = findViewById(R.id.semester_id);
        semester_name = findViewById(R.id.semester_name);

        Button registerSemester = (Button) findViewById(R.id.register_semester);
        registerSemester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getString(semester_id)) || TextUtils.isEmpty(getString(semester_name))){
                    Toast.makeText(RegisterSemester.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    registerSemester();
                }

            }
        });
    }

    private void registerSemester() {
        final Semesters semester = new Semesters(getString(semester_id), getString(semester_name), "12345");
        databaseSemesters.child(getString(semester_id))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(RegisterSemester.this, "Semester Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseSemesters.child(getString(semester_id))
                                    .setValue(semester)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterSemester.this, "Semester Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterSemester.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

