package com.example.madproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madproject.classes.CPUBuild;
import com.example.madproject.classes.CPUs;
import com.example.madproject.classes.Casing;
import com.example.madproject.selections.casingselection;
import com.example.madproject.selections.coolselection;
import com.example.madproject.selections.cpuselectionNew;
import com.example.madproject.selections.gpuselection;
import com.example.madproject.selections.hddselection;
import com.example.madproject.selections.mbselection;
import com.example.madproject.selections.psselection;
import com.example.madproject.selections.ramselection;
import com.example.madproject.selections.ssdselection;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfPC extends AppCompatActivity {

    EditText CPUSelect;
    EditText CasingSelect;
    Button cpubtn;
    Button casingbtn;
    Button rambtn;
    Button motherboard;
    Button gpubtn;
    Button hddbtn;
    Button ssdbtn;
    Button psbtn;
    Button coolbtn;
    Button savebtn;
    Integer Total=0;
    TextView BuildTotal;
    int LAUNCH_SECOND_ACTIVITY = 1;
    int LAUNCH_THIRD_ACTIVITY = 1;
    int LAUNCH_FOURTH_ACTIVITY = 1;
    int LAUNCH_FIFTH_ACTIVITY = 1;
    int LAUNCH_SIXTH_ACTIVITY = 1;
    int LAUNCH_SEVENTH_ACTIVITY = 1;
    int LAUNCH_EIGHTH_ACTIVITY = 1;
    int LAUNCH_NINTH_ACTIVITY = 1;
    int LAUNCH_TENTH_ACTIVITY = 1;
    CPUs cpUs;
    Casing casing;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_p_c);
        cpubtn = findViewById(R.id.CPUSelect);
        rambtn = findViewById(R.id.RAMSelect);
        motherboard = findViewById(R.id.MBSelect);
        gpubtn = findViewById(R.id.GPUSelect);
        hddbtn = findViewById(R.id.HDDSelect);
        ssdbtn = findViewById(R.id.SSDSelect);
        psbtn = findViewById(R.id.PSSelect);
        coolbtn = findViewById(R.id.COOLSelect);
        casingbtn = findViewById(R.id.CasingSelect);
        savebtn = findViewById(R.id.SaveBtn);
        CPUSelect = findViewById(R.id.CPUText);
        CasingSelect = findViewById(R.id.CasingText);
        BuildTotal = findViewById(R.id.TotalBuild);

        databaseReference = FirebaseDatabase.getInstance().getReference();


    }

    private void writeNewBuild(Casing casing, CPUs cpu) {
        CPUBuild cpuBuild = new CPUBuild(casing, cpu);

       databaseReference.child("CPUBuilds").push().setValue(cpuBuild);

    }

    @Override
    protected void onResume() {
        super.onResume();

        cpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, cpuselectionNew.class);
                startActivityForResult(intent,LAUNCH_THIRD_ACTIVITY);
            }
        });

        casingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, casingselection.class);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });


        rambtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, ramselection.class);
                startActivityForResult(intent, LAUNCH_THIRD_ACTIVITY);
            }
        });

        motherboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, mbselection.class);
                startActivityForResult(intent, LAUNCH_FOURTH_ACTIVITY);
            }
        });

        gpubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, gpuselection.class);
                startActivityForResult(intent, LAUNCH_FIFTH_ACTIVITY);
            }
        });

        hddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, hddselection.class);
                startActivityForResult(intent, LAUNCH_SIXTH_ACTIVITY);
            }
        });

        ssdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, ssdselection.class);
                startActivityForResult(intent, LAUNCH_SEVENTH_ACTIVITY);
            }
        });

        psbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, psselection.class);
                startActivityForResult(intent, LAUNCH_EIGHTH_ACTIVITY);
            }
        });

        coolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfPC.this, coolselection.class);
                startActivityForResult(intent, LAUNCH_NINTH_ACTIVITY);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewBuild(casing,cpUs);
                Intent intent = new Intent(v.getContext(),savedConfigNew.class);
                startActivity(intent);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAGS","onActivity Called1");
        if (requestCode == LAUNCH_SECOND_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CasingPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CasingPrice",0);
                    Log.d("Casing Price",""+data.getIntExtra("CasingPrice",0));
                    casing = (Casing) data.getSerializableExtra("Casing Object");
                    Log.d("Casing Object",casing.getName());

                }
                if (data.getStringExtra("CasingName") != null){
                    Log.d("Casing name 2:",data.getStringExtra("CasingName"));
                    CasingSelect.setText(data.getStringExtra("CasingName"));
                }
            }
        }
        if (requestCode == LAUNCH_THIRD_ACTIVITY){
            Log.d("TAGS","onActivity Called");
            if (resultCode == Activity.RESULT_OK){
                if(data.getIntExtra("CPUPrice",0) !=  0){
                    Total = Total + data.getIntExtra("CPUPrice",0);
                    cpUs = (CPUs)data.getSerializableExtra("CPU Object");
                    Log.d("CPU Object",cpUs.getName());
                }
                if (data.getStringExtra("CPUName") != null){
                    CPUSelect.setText(data.getStringExtra("CPUName"));
                }
            }
        }
    }
}