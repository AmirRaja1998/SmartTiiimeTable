package com.crissalex.smarttimetable;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crissalex.smarttimetable.model.Slots;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterSlot extends AppCompatActivity {
    DatabaseReference databaseSlot;
    EditText slot_id, slot_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_slot);

        setTitle("Register Slot");

        databaseSlot = FirebaseDatabase.getInstance().getReference("Courses");
        slot_id = findViewById(R.id.slot_id);
        slot_name = findViewById(R.id.slot_name);

        Button registerSlot = (Button) findViewById(R.id.register_slot);
        registerSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(getString(slot_id)) || TextUtils.isEmpty(getString(slot_name))){
                    Toast.makeText(RegisterSlot.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    registerSlot();
                }

            }
        });
    }

    private void registerSlot() {
        final Slots slot = new Slots(getString(slot_id), getString(slot_name), "1234567");
        databaseSlot.child(getString(slot_id))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            Toast.makeText(RegisterSlot.this, "Slot Already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseSlot.child(getString(slot_id))
                                    .setValue(slot)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterSlot.this, "Slot Registered Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterSlot.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
