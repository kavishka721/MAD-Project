package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class cpuselection extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private com.example.madproject.classes.CPU CPU;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuselection);
        final ArrayList<com.example.madproject.classes.CPU> CPUList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    System.out.println("Hello CPUS");
                    if (dataSnapshot.hasChildren()) {
                        CPU = dataSnapshot.child("CPUs").child("CPU1").getValue(com.example.madproject.classes.CPU.class);
                        System.out.println("Base Clock : "+CPU.getBase_Clock()+"GHz");
                        CPUList.add(CPU);
                        Log.i("CPU List","");
                        //Log.i("Values", dataSnapshot.child("CPU1").child("Name").getValue().toString());
                    }
                }
                else
                    Log.i("Info B","Nothing bn");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}