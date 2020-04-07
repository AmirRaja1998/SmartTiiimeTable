package com.crissalex.smarttimetable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crissalex.smarttimetable.model.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterTeacher extends AppCompatActivity {
    DatabaseReference databaseTeachers;
    EditText teacher_id, teacher_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        setTitle("Register Teacher");

        databaseTeachers = FirebaseDatabase.getInstance().getReference("Teachers");
        teacher_id = findViewById(R.id.teacher_id);
        teacher_name = findViewById(R.id.teacher_name);

        Button registerTeacher = (Button) findViewById(R.id.register_teacher);
        registerTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getString(teacher_id)) || TextUtils.isEmpty(getString(teacher_name))){
                    Toast.makeText(RegisterTeacher.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    registerTeacher();
                }

            }
        });
    }

    private void registerTeacher() {
        final Teacher teacher = new Teacher(getString(teacher_id), getString(teacher_name), "1234");
        databaseTeachers.child(getString(teacher_id))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(RegisterTeacher.this, "Teacher Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseTeachers.child(getString(teacher_id))
                                    .setValue(teacher)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterTeacher.this, "Teacher Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterTeacher.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
